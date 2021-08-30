package com.andriod.egroweed.controller;


import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.GreenhouseRoomDao;
import com.andriod.egroweed.model.dao.PlantRoomDao;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterGreenHouseFormFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterGreenHousesCardFragment;

import java.util.List;
import java.util.Random;


public class DashboardEgrowerMasterController {

    private GreenhouseRoomDao greenhouseRoomDao;
    private PlantRoomDao plantRoomDao;

    public List<Greenhouse> findGreenhousesByEmail(DashboardEgrowerMasterFragment fragment, String email) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        List<Greenhouse> greenhouses = greenhouseRoomDao.getGreenhouseByEmail(email);
        return greenhouses;
    }

    public void deleteGreenhouseByName(DashboardEgrowerMasterGreenHousesCardFragment fragment, String name) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = greenhouseRoomDao.getGreenhouseByName(name);
        greenhouseRoomDao.deleteOne(greenhouse);
        fragment.deleteGreenhouseSucceed(greenhouse);
    }

    public void createGreenHouse(DashboardEgrowerMasterGreenHouseFormFragment fragment, String name, Integer capacity, String location, String owner){
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = new Greenhouse();
        greenhouse.setName(name);
        greenhouse.setCapacity(capacity);
        greenhouse.setLocation(location);
        greenhouse.setOwner(owner);
        Random r = new Random();
        int low = 1;
        int high = 5;
        int result = r.nextInt(high-low) + low;
        greenhouse.setAvatar(result);
        this.greenhouseRoomDao.insertOne(greenhouse);
        fragment.createSucceed(greenhouse);
    }

    public void updateGreenhouse(DashboardEgrowerMasterGreenHousesCardFragment fragment, String nameOld, String nameNew, Integer capacity, String location) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = this.greenhouseRoomDao.getGreenhouseByName(nameOld);
        greenhouse.setName(nameNew);
        greenhouse.setCapacity(capacity);
        greenhouse.setLocation(location);
        this.greenhouseRoomDao.updateOne(greenhouse);
        fragment.updateGreenhouseSucceed(greenhouse);
    }

    public List<Plant> findPlantsByGreenhouse(DashboardEgrowerMasterGreenHousesCardFragment fragment, Integer greenhouseId) {
        this.plantRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).plantRoomDao();
        List<Plant> plants = this.plantRoomDao.getPlantsByGreenhouseId(greenhouseId);
        return plants;
    }
}
