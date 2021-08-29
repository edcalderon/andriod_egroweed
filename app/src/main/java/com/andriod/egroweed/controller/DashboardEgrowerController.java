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
import com.andriod.egroweed.view.fragments.DashboardEgrowerSponsoredPlantsCardFragment;


import java.util.List;

public class DashboardEgrowerController {
    private GreenhouseRoomDao greenhouseRoomDao;
    private UserRoomDao userRoomDao;
    private PlantRoomDao plantRoomDao;

    public List<Greenhouse> getAllGreenhouses(DashboardEgrowerFragment fragment) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        List<Greenhouse> greenhouses = greenhouseRoomDao.getAll();
        return greenhouses;
    }

    public Greenhouse getGreenhouseById(Fragment fragment, Integer id) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = greenhouseRoomDao.getGreenhouseById(id);
        return greenhouse;
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
            Plant plant = new Plant();
            plant.setOwner(user.getEmail());
            plant.setGreenhouse(greenhouse.getName());
            plant.setQuantity(plantsToSponsor);
            greenhouse.setCapacity(greenhouse.getCapacity()-plantsToSponsor);
            this.plantRoomDao.insertOne(plant);
            float actualUserBalance = user.getWallet().getBalance();
            float newBalance = actualUserBalance - plantsToSponsor*300;
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
    public void earlySoldPlant(DashboardEgrowerSponsoredPlantsCardFragment fragment, Plant plant){
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        plantRoomDao.deleteOne(plant);
        fragment.earlySoldPlantSucceed(plant);
    }

    public Plant getPlantById(Fragment fragment, Long id) {
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        Plant plant = plantRoomDao.getPlantById(id);
        return plant;
    }
}
