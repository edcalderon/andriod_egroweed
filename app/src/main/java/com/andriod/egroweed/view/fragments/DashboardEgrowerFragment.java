package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;


public class DashboardEgrowerFragment extends Fragment {


    public DashboardEgrowerFragment() {
        // Required empty public constructor
    }


    public static DashboardEgrowerFragment newInstance(String param1, String param2) {
        DashboardEgrowerFragment fragment = new DashboardEgrowerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_egrower, container, false);
    }
}