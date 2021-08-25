package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Greenhouse;

import java.util.List;


public class DashboardEgrowerFragment extends Fragment {
    private String name;
    private String roll;
    private Integer avatar;
    private View rootView;

    public DashboardEgrowerFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerFragment newInstance() {
        DashboardEgrowerFragment fragment = new DashboardEgrowerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name =  getArguments().getString("name");
        avatar =  getArguments().getInt("avatar");
        roll =  getArguments().getString("roll");
        getChildFragmentManager().beginTransaction().replace(R.id.egrower_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll)).commit();
        DashboardEgrowerController dashboardEgrowerController = new DashboardEgrowerController();
        List<Greenhouse> greenhouses = dashboardEgrowerController.getAllGreenhouses(this);
        if(!greenhouses.isEmpty()){
            for (Greenhouse greenhouse: greenhouses) {
                String owner = greenhouse.getOwner();
                String name = greenhouse.getName();
                String capacity = greenhouse.getCapacity();
                String location = greenhouse.getLocation();
                getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_horizontal_scroll, DashboardEgrowerGreenhouseCardFragment.newInstance(owner,name,capacity,location)).commit();
            }
        }
        getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredplantsEmptyFragment.newInstance()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower, container, false);
        return rootView;
    }

}