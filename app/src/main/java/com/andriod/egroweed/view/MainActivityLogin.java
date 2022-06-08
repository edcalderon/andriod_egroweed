package com.andriod.egroweed.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.MainActivityControllerLogin;
import com.andriod.egroweed.model.pojo.User;

import es.dmoral.toasty.Toasty;

public class MainActivityLogin extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private MainActivityControllerLogin mainActivityControllerLogin;
    private Button loginButton;
    private TextView registerText;
    public static final String SESSION = "MyPrefs" ;
    public static final String Email = "emailKey";
    public static final String Name = "nameKey";
    public static final String Roll = "rollKey";
    public static final String Balance = "balanceKey";
    public static final String Avatar = "avatarKey";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Egroweed);
        super.onCreate(savedInstanceState);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        setContentView(R.layout.activity_main_login);
        emailEditText = findViewById(R.id.editTextEmail_login);
        passwordEditText = findViewById(R.id.editTextTextPassword_login);
        loginButton = findViewById(R.id.button_login_loginView);
        registerText = findViewById(R.id.textView_register_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyPassword = passwordEditText.getText().toString().isEmpty();
                if(emptyPassword) {
                    Toasty.warning(getApplicationContext(), "Enter a password", Toast.LENGTH_SHORT, true).show();
                }
                if(emailEditText.getText().toString().isEmpty()) {
                    Toasty.warning(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT, true).show();
                }else {
                    if (emailEditText.getText().toString().trim().matches(emailPattern) && !emptyPassword) {
                        login();
                    } else {
                        Toasty.error(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(getApplicationContext(), MainActivityRegister.class);
                startActivity(newActivity);
            }
        });

        Window window = MainActivityLogin.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        getSupportActionBar().hide();
        mainActivityControllerLogin = new MainActivityControllerLogin();
    }

    public void login(){
        mainActivityControllerLogin.login(this,
                emailEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    public void loginSucceed(User user){
        SharedPreferences sharedpreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Email, user.getEmail());
        editor.putString(Name, user.getName());
        editor.putString(Roll, user.getRoll());
        editor.putInt(Avatar, user.getAvatar());
        editor.putFloat(Balance,user.getWallet().getBalance());
        editor.apply();
        if(user.getRoll().compareTo("E-grower")==0){
            Intent newActivity = new Intent(this, Dashboard.class);
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }
        if(user.getRoll().compareTo("E-grower Master")==0){
            Intent newActivity = new Intent(this, DashboardEgrowerMaster.class);
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }
    }

    public void loginFail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please verify your email or password.")
                .setTitle("Something went wrong!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}