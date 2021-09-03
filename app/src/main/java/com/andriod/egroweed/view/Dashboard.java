package com.andriod.egroweed.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.view.fragments.DashboardEgrowerFragment;
import com.andriod.egroweed.view.fragments.ProfileFragment;
import com.andriod.egroweed.view.fragments.WalletFragment;


import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class Dashboard extends AppCompatActivity  {
    private SmoothBottomBar bottomBar;
    private DashboardEgrowerController dashboardEgrowerController;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dashboardEgrowerController = new DashboardEgrowerController();
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
                        replace(new WalletFragment());
                        break;
                    case 2:
                        replace(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
        setTitle(R.string.dashboard_egrower);
        Window window = Dashboard.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purple_500));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.purple_500));
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    public void moveBottomBarOnSelect(Integer i){
        bottomBar.setItemActiveIndex(i);

    }
    public void replace(Fragment fragment) {
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivityRegister.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        float sessionBalance = sharedpreferences.getFloat("balanceKey", (float)0.0);
        String userRoll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;


        Bundle bundle = new Bundle();
        bundle.putString("name", sessionName);
        bundle.putString("email", sessionEmail);
        bundle.putString("roll", userRoll);
        bundle.putInt("avatar", userAvatar);
        bundle.putFloat("balance",sessionBalance);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_dashboard, fragment);
        transaction.commit();
    }
}
