package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;

public class DashboardEgrowerSponsoredplantsEmptyFragment extends Fragment {

    public DashboardEgrowerSponsoredplantsEmptyFragment() {
        // Required empty public constructor
    }


    public static DashboardEgrowerSponsoredplantsEmptyFragment newInstance() {
        DashboardEgrowerSponsoredplantsEmptyFragment fragment = new DashboardEgrowerSponsoredplantsEmptyFragment();
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
        return inflater.inflate(R.layout.fragment_dashboard_egrower_sponsoredplants_empty, container, false);
    }
}