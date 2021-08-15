package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.view.Profile;
import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.pojo.User;


public class ProfileController {
    private UserRoomDao userRoomDao;

    public void updateUser(Profile profile, String email, String name){
        this.userRoomDao = LocalStorage.getLocalStorage(profile.getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        user.setName(name);
        this.userRoomDao.updateOne(user);
        profile.updateUserSucceed(user);
    }
}
