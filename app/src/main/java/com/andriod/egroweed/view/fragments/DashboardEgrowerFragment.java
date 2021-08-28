package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.MainActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;


public class DashboardEgrowerFragment extends Fragment {
    private String name;
    private String roll;
    private Integer avatar;
    private Float balance;
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
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String owner = sharedpreferences.getString("emailKey", "" );
        name =  getArguments().getString("name");
        avatar =  getArguments().getInt("avatar");
        roll =  getArguments().getString("roll");
        balance =  getArguments().getFloat("balance");
        getChildFragmentManager().beginTransaction().replace(R.id.egrower_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll, balance)).commit();
        DashboardEgrowerController dashboardEgrowerController = new DashboardEgrowerController();
        List<Greenhouse> greenhouses = dashboardEgrowerController.getAllGreenhouses(this);
        List<Plant> plants = dashboardEgrowerController.getAllPlantsByOwner(this, owner);
        if(!greenhouses.isEmpty()){
            for (Greenhouse greenhouse: greenhouses) {
                String greenhouseOwner = greenhouse.getOwner();
                String name = greenhouse.getName();
                Integer capacity = greenhouse.getCapacity();
                String location = greenhouse.getLocation();
                Integer avatarIndex = greenhouse.getAvatar();
                Integer id = greenhouse.getId();
                getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_horizontal_scroll, DashboardEgrowerGreenhouseCardFragment.newInstance(greenhouseOwner,name,id,capacity,location,avatarIndex)).commit();
            }
        } else {
            getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_horizontal_scroll, DashboardEgrowerGreenhousesEmptyFragment.newInstance()).commit();
        }
        if (!plants.isEmpty()){
            for (Plant plant: plants) {
                Integer quantity = plant.getQuantity();
                getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredPlantsFragment.newInstance(quantity)).commit();
            }
        }
        if (plants.isEmpty()){
            getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredplantsEmptyFragment.newInstance(), "EMPTY").commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower, container, false);
        return rootView;
    }

}