package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.model.pojo.Wallet;
import com.andriod.egroweed.view.fragments.ProfileFragment;
import com.andriod.egroweed.view.fragments.WalletFragment;

public class WalletFragmentController {
    private UserRoomDao userRoomDao;
    
    public void updateUserBalance(WalletFragment walletFragment, Float newBalance, String email){
        this.userRoomDao = LocalStorage.getLocalStorage(walletFragment.getActivity().getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        Wallet wallet = new Wallet();
        wallet.setBalance(newBalance);
        user.setWallet(wallet);
        this.userRoomDao.updateOne(user);
        walletFragment.updateUserBalanceSucceed(user.getWallet().getBalance());
    }
    public void addUserBalance(WalletFragment walletFragment, Float newBalance, String email){
        this.userRoomDao = LocalStorage.getLocalStorage(walletFragment.getActivity().getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        Wallet wallet = new Wallet();
        wallet.setBalance(user.getWallet().getBalance() + newBalance);
        user.setWallet(wallet);
        this.userRoomDao.updateOne(user);
        walletFragment.updateUserBalanceSucceed(user.getWallet().getBalance());
    }
}
