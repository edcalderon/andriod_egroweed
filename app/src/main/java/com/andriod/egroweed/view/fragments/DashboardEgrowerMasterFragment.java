package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andriod.egroweed.R;

public class DashboardEgrowerMasterFragment extends Fragment {
    private String name;
    private Integer avatar;
    private TextView nameTextView;
    private View rootView;

    public DashboardEgrowerMasterFragment() {
        // Required empty public constructor
    }


    public static DashboardEgrowerMasterFragment newInstance(String param1, String param2) {
        DashboardEgrowerMasterFragment fragment = new DashboardEgrowerMasterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name =  getArguments().getString("name");
        avatar =  getArguments().getInt("avatar");
        getChildFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment_dashboard, UserInformationFragmentDashboard.newInstance(name, avatar)).commit();
        /*for(int i=0; i<10;i++){
            getChildFragmentManager().beginTransaction().add(R.id.egrower_master_menu_linear_layout, UserInformationFragmentDashboard.newInstance(name + "-" + i, avatar)).commit();
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master, container, false);
        return rootView;
    }
}