package com.andriod.egroweed.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.EgrowerDashboardController;
import com.andriod.egroweed.view.fragments.DashboardEgrowerFragment;
import com.andriod.egroweed.view.fragments.ProfileFragment;
import com.andriod.egroweed.view.fragments.SettingsFragment;


import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class EgrowerDashboard extends AppCompatActivity  {
    private SmoothBottomBar bottomBar;
    private EgrowerDashboardController egrowerDashboardController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egrower_dashboard);
        egrowerDashboardController = new EgrowerDashboardController();
        replace(new DashboardEgrowerFragment());
        bottomBar = findViewById(R.id.bottomBar);
        getSupportActionBar().hide();
        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i){
                    case 0:
                        replace(new DashboardEgrowerFragment());
                        break;
                    case 1:
                        replace(new ProfileFragment());
                        break;
                    case 2:
                        replace(new SettingsFragment());
                        break;
                }
                return true;
            }
        });
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userEmail = sessionName != null ? sessionName : sessionEmail;
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        setTitle(R.string.dashboard_egrower);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_500));
        }
    }

    private void replace(Fragment fragment) {
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userRoll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;

        Bundle bundle = new Bundle();
        bundle.putString("name", sessionName);
        bundle.putString("email", sessionEmail);
        bundle.putString("roll", userRoll);
        bundle.putInt("avatar", userAvatar);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_dashboard, fragment);
        transaction.commit();
    }
    public void logout(){
        AlertDialog.Builder builder_logout = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you want to exit?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder_logout.create().show();
    }
}
