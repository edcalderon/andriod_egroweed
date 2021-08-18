package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.view.Profile;
import com.andriod.egroweed.view.fragments.ProfileFragment;


public class ProfileFragmentController {
    private UserRoomDao userRoomDao;

    public void updateUser(ProfileFragment profile, String email, String name){
        this.userRoomDao = LocalStorage.getLocalStorage(profile.getActivity().getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        user.setName(name);
        this.userRoomDao.updateOne(user);
        profile.updateUserSucceed(user);
    }
}
