package com.andriod.egroweed.controller;


import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.GreenhouseRoomDao;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterGreenHouseFormFragment;
import com.andriod.egroweed.view.fragments.DashboardEgrowerMasterGreenHousesCardFragment;

import java.util.List;


public class DashboardEgrowerMasterController {

    private GreenhouseRoomDao greenhouseRoomDao;

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

    public void createGreenHouse(DashboardEgrowerMasterGreenHouseFormFragment fragment, String name, String capacity, String location, String owner){
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = new Greenhouse();
        greenhouse.setName(name);
        greenhouse.setCapacity(capacity);
        greenhouse.setLocation(location);
        greenhouse.setOwner(owner);
        this.greenhouseRoomDao.insertOne(greenhouse);
        fragment.createSucceed(greenhouse);
    }

    public void updateGreenhouse(DashboardEgrowerMasterGreenHousesCardFragment fragment, String nameOld, String nameNew, String capacity, String location) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        Greenhouse greenhouse = this.greenhouseRoomDao.getGreenhouseByName(nameOld);
        greenhouse.setName(nameNew);
        greenhouse.setCapacity(capacity);
        greenhouse.setLocation(location);
        this.greenhouseRoomDao.updateOne(greenhouse);
        fragment.updateGreenhouseSucceed(greenhouse);
    }
}
