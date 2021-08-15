package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.view.MainActivityLogin;
import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.pojo.User;


public class MainActivityControllerLogin {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;

    public void login(MainActivityLogin mainActivityLogin, String email, String password){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityLogin.getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        if(user.getPassword().compareTo(password)==0){
            mainActivityLogin.loginSucceed(user);
        } else {
            mainActivityLogin.loginFail(user);
        }
    }
}
