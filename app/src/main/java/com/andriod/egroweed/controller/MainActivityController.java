package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.pojo.Wallet;
import com.andriod.egroweed.view.MainActivity;
import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.pojo.User;


public class MainActivityController {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;

    public User checkActualUser(MainActivity mainActivity){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUser();
        if (user != null){
            return user;
        }
        return null;
    }

    public String checkUserEmail(MainActivity mainActivity, String email){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUserByEmail(email);
        if (user != null){
            return user.getEmail();
        }
        return null;
    }

    public void register(MainActivity mainActivity, String email, String password, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setRoll(roll);
        Wallet wallet = new Wallet();
        wallet.setBalance((float)0.012);
        user.setWallet(wallet);
        this.userRoomDao.insertOne(user);
        mainActivity.registerSucceed(user);
    }

    public void updateRegisteredUser(MainActivity mainActivity, String email, String password, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setRoll(roll);
        this.userRoomDao.updateOne(user);
        mainActivity.registerSucceed(user);
    }

}
