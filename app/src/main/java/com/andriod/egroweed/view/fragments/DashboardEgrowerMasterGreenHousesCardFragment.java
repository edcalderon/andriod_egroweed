package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;

public class DashboardEgrowerMasterGreenHousesFragment extends Fragment {
   private View rootView;

    public DashboardEgrowerMasterGreenHousesFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerMasterGreenHousesFragment newInstance() {
        DashboardEgrowerMasterGreenHousesFragment fragment = new DashboardEgrowerMasterGreenHousesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master_green_houses, container, false);
        return rootView;
    }
}