package com.andriod.egroweed.controller;

import com.andriod.egroweed.model.LocalStorage;
import com.andriod.egroweed.model.dao.GreenhouseRoomDao;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.view.fragments.DashboardEgrowerFragment;


import java.util.List;

public class DashboardEgrowerController {
    private GreenhouseRoomDao greenhouseRoomDao;

    public List<Greenhouse> getAllGreenhouses(DashboardEgrowerFragment fragment) {
        this.greenhouseRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).greenhouseRoomDao();
        List<Greenhouse> greenhouses = greenhouseRoomDao.getAll();
        return greenhouses;
    }
}
