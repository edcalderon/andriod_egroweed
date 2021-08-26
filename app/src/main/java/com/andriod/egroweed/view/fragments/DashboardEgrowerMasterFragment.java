package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerMasterController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

public class DashboardEgrowerMasterFragment extends Fragment {
    private String name;
    private String email;
    private String roll;
    private Integer avatar;
    private View rootView;
    private FloatingActionButton FabAddGreenHouse;
    private FloatingActionsMenu FaMenu;

    public DashboardEgrowerMasterFragment() {
        // Required empty public constructor
    }


    public static DashboardEgrowerMasterFragment newInstance() {
        DashboardEgrowerMasterFragment fragment = new DashboardEgrowerMasterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name =  getArguments().getString("name");
        email =  getArguments().getString("email");
        avatar =  getArguments().getInt("avatar");
        roll =  getArguments().getString("roll");
        getChildFragmentManager().beginTransaction().replace(R.id.egrower_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll)).commit();

        DashboardEgrowerMasterController dashboardEgrowerMasterFragmentController = new DashboardEgrowerMasterController();
        List<Greenhouse> userGreenhouses = dashboardEgrowerMasterFragmentController.findGreenhousesByEmail(this, email);
        if(!userGreenhouses.isEmpty()){
            for (Greenhouse greenhouse: userGreenhouses){
                String owner = greenhouse.getOwner();
                String name = greenhouse.getName();
                String capacity = greenhouse.getCapacity();
                String location = greenhouse.getLocation();
                Integer avatarIndex = greenhouse.getAvatar();
                getChildFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerMasterGreenHousesCardFragment.newInstance(owner,name,capacity,location, avatarIndex)).commit();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master, container, false);
        FabAddGreenHouse = rootView.findViewById(R.id.fab_action1);
        FaMenu = rootView.findViewById(R.id.floatingActionsMenu);
        FabAddGreenHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerMasterGreenHouseFormFragment.newInstance(email)).commit();
                FaMenu.collapse();
                FaMenu.setVisibility(View.GONE);
            }
        });
        return rootView;
    }
}