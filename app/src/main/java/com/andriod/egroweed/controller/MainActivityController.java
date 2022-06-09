package com.andriod.egroweed.controller;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.pojo.Wallet;
import com.andriod.egroweed.view.MainActivityRegister;
import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class MainActivityController {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;
    private FirebaseAuth mAuth;

    public User checkActualUser(MainActivityRegister mainActivityRegister){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityRegister.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUser();
        if (user != null){
            return user;
        }
        return null;
    }

    public String checkUserEmail(MainActivityRegister mainActivityRegister, String email){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityRegister.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUserByEmail(email);
        if (user != null){
            return user.getEmail();
        }
        return null;
    }

    public void firebaseRegister(MainActivityRegister mainActivityRegister, String email, String password, User user){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mainActivityRegister.registerSucceed(user);
            }
        });
    }

    public void register(MainActivityRegister mainActivityRegister, String email, String password, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityRegister.getApplicationContext()).userRoomDao();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setRoll(roll);
        Wallet wallet = new Wallet();
        wallet.setBalance((float)0.012);
        user.setWallet(wallet);
        this.userRoomDao.insertOne(user);
        // mainActivityRegister.registerSucceed(user);
        // Firebase register
        firebaseRegister(mainActivityRegister, email, password, user);
    }




    public void updateRegisteredUser(MainActivityRegister mainActivityRegister, String email, String password, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityRegister.getApplicationContext()).userRoomDao();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setRoll(roll);
        this.userRoomDao.updateOne(user);
        mainActivityRegister.registerSucceed(user);
    }

}
