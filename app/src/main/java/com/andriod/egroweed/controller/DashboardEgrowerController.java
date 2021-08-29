package com.andriod.egroweed.controller;

import androidx.fragment.app.Fragment;

import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.GreenhouseRoomDao;
import com.andriod.egroweed.model.dao.UserRoomDao;
import com.andriod.egroweed.model.dao.PlantRoomDao;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.model.pojo.Wallet;
import com.andriod.egroweed.view.fragments.DashboardEgrowerConfirmSponsorFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerSponsoredCardSucceedFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerSponsoredPlantsCardFragment;


import java.util.List;

public class DashboardEgrowerController {
    private GreenhouseRoomDao greenhouseRoomDao;
    private UserRoomDao userRoomDao;
    private PlantRoomDao plantRoomDao;

    public List<Greenhouse> getAllGreenhouses(Fragment fragment) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        List<Greenhouse> greenhouses = greenhouseRoomDao.getAll();
        return greenhouses;
    }

    public void sponsorPlant(DashboardEgrowerConfirmSponsorFragment fragment, Integer plantsToSponsor, Integer greenhouseID, String owner) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        this.userRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).userRoomDao();
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        Greenhouse greenhouse = greenhouseRoomDao.getGreenhouseById(greenhouseID);
        User user = userRoomDao.getUserByEmail(owner);
        if ( greenhouse.getCapacity() < 0){
            fragment.errorSponsoringPlant();
        } else if ( greenhouse.getCapacity() >= plantsToSponsor) {
            Float plantCost = plantsToSponsor * (float)300;
            Plant plant = new Plant();
            plant.setOwner(user.getEmail());
            plant.setGreenhouse(greenhouse.getName());
            plant.setQuantity(plantsToSponsor);
            plant.setPrice(plantCost);
            plant.setGreenhouseId(greenhouseID);
            greenhouse.setCapacity(greenhouse.getCapacity()-plantsToSponsor);
            this.plantRoomDao.insertOne(plant);
            float actualUserBalance = user.getWallet().getBalance();
            float newBalance = actualUserBalance - plantCost;
            Wallet wallet = new Wallet();
            wallet.setBalance(newBalance);
            user.setWallet(wallet);
            this.userRoomDao.updateOne(user);
            this.greenhouseRoomDao.updateOne(greenhouse);
            fragment.successSponsoringPlant(plantsToSponsor, newBalance);
        } else {
            fragment.errorSponsoringPlant();
        }
    }

    public List<Plant> getAllPlantsByOwner(Fragment fragment, String ownerEmail) {
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        List<Plant> plants = plantRoomDao.getPlantsByOwner(ownerEmail);
        return plants;
    }
    public void earlySellPlant(DashboardEgrowerSponsoredPlantsCardFragment fragment, Plant plant, String owner, Integer greenhouseId){
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        this.userRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).userRoomDao();
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = greenhouseRoomDao.getGreenhouseById(greenhouseId);
        greenhouse.setCapacity(greenhouse.getCapacity()+ plant.getQuantity());
        User user = userRoomDao.getUserByEmail(owner);
        float actualUserBalance = user.getWallet().getBalance();
        float newBalance = actualUserBalance + plant.getPrice();
        Wallet wallet = new Wallet();
        wallet.setBalance(newBalance);
        user.setWallet(wallet);
        this.userRoomDao.updateOne(user);
        this.greenhouseRoomDao.updateOne(greenhouse);
        plantRoomDao.deleteOne(plant);
        fragment.earlySoldPlantSucceed(plant, newBalance);
    }

    public void sellCrop(DashboardEgrowerSponsoredCardSucceedFragment fragment, Long plantId){
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        this.userRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).userRoomDao();
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Plant plant = plantRoomDao.getPlantById(plantId);
        Greenhouse greenhouse = greenhouseRoomDao.getGreenhouseById(plant.getGreenhouseId());
        greenhouse.setCapacity(greenhouse.getCapacity() + plant.getQuantity());
        User user = userRoomDao.getUserByEmail(plant.getOwner());
        float actualUserBalance = user.getWallet().getBalance();
        float earning = plant.getPrice()* (float)0.13;
        float newBalance = actualUserBalance + (plant.getPrice() + earning);
        Wallet wallet = new Wallet();
        wallet.setBalance(newBalance);
        user.setWallet(wallet);
        this.userRoomDao.updateOne(user);
        this.greenhouseRoomDao.updateOne(greenhouse);
        plantRoomDao.deleteOne(plant);
        fragment.sellCropSucceed(plant, newBalance, earning);
    }

    public Plant getPlantById(Fragment fragment, Long id) {
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        Plant plant = plantRoomDao.getPlantById(id);
        return plant;
    }
}
