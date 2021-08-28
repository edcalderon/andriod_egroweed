package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andriod.egroweed.R;


public class DashboardEgrowerSponsoredPlantsFragment extends Fragment {
    private View rootView;
    private TextView textViewSponsoredPlants;
    private Integer numberOfSponsoredPlants;

    public DashboardEgrowerSponsoredPlantsFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerSponsoredPlantsFragment newInstance(Integer sponsoredPlants) {
        DashboardEgrowerSponsoredPlantsFragment fragment = new DashboardEgrowerSponsoredPlantsFragment();
        fragment.setNumberOfSponsoredPlants(sponsoredPlants);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_sponsored_plants_card, container, false);
        textViewSponsoredPlants = rootView.findViewById(R.id.textView_sponsored_plants_card_sponsored_plants);
        textViewSponsoredPlants.setText(getNumberOfSponsoredPlants().toString());
        return rootView;
    }

    public Integer getNumberOfSponsoredPlants() {
        return numberOfSponsoredPlants;
    }

    public void setNumberOfSponsoredPlants(Integer numberOfSponsoredPlants) {
        this.numberOfSponsoredPlants = numberOfSponsoredPlants;
    }
}