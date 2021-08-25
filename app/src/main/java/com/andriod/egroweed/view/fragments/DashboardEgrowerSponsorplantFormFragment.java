package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andriod.egroweed.R;


public class DashboardEgrowerSponsorplantFormFragment extends Fragment {

    private View rootView;


    public DashboardEgrowerSponsorplantFormFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerSponsorplantFormFragment newInstance() {
        DashboardEgrowerSponsorplantFormFragment fragment = new DashboardEgrowerSponsorplantFormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_sponsorplant_form, container, false);
        return rootView;
    }
}