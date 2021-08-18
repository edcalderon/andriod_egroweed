package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;


import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.EgrowerDashboardController;


public class SettingsFragment extends Fragment {
    private View rootView;
    private ImageButton logoutButton;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        logoutButton = rootView.findViewById(R.id.imageButton_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            private Object EgrowerDashboard;
            @Override
            public void onClick(View v) {
                EgrowerDashboardController.logout((com.andriod.egroweed.view.EgrowerDashboard) EgrowerDashboard);
            }
        });
        return rootView;
    }

}