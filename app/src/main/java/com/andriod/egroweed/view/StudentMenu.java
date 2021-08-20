package com.andriod.egroweed.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.andriod.egroweed.view.fragments.UserInformationFragment;
import com.andriod.egroweed.R;
import com.google.android.material.navigation.NavigationView;

import es.dmoral.toasty.Toasty;

public class StudentMenu extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.app_nav_open, R.string.app_nav_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.nav_menu_profile){
                    Toasty.success(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                }
                else if(id == R.id.nav_menu_dashboard){
                    Toasty.success(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                }
                /*else if(id == R.id.nav_menu_settings){
                    Toasty.success(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                }*/
                return false;
            }
        });
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userEmail = sessionName != null ? sessionName : sessionEmail;
        String userRoll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment_dashboard, UserInformationFragment.newInstance(userEmail, userAvatar, userRoll)).commit();
        setTitle(R.string.dashboard_egrower);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userEmail = sessionName != null ? sessionName : sessionEmail;
        String userRoll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment_dashboard, UserInformationFragment.newInstance(userEmail, userAvatar, userRoll)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }
    public void logout(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.configuration_button_student_menu_dashboard:
                Intent newActivity = new Intent(this, Profile.class);
                newActivity.putExtra("userName", getIntent().getExtras().getString("userName") != null ? getIntent().getExtras().getString("userName") : "");
                newActivity.putExtra("userEmail", getIntent().getExtras().getString("userEmail") != null ? getIntent().getExtras().getString("userEmail") : "");
                newActivity.putExtra("userAvatar", getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0);
                newActivity.putExtra("userRoll", getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "");
                startActivity(newActivity);;
                return true;
            case R.id.configuration_button_student_menu_logout:
                AlertDialog.Builder builder_logout = new AlertDialog.Builder(this)
                        .setTitle("Exit")
                        .setMessage("Do you want to exit?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                logout();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder_logout.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}