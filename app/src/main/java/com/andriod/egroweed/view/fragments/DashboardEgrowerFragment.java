package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;


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
        getChildFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll)).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_egrower, container, false);
    }
}