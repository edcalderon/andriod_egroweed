package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.andriod.egroweed.R;
import com.andriod.egroweed.view.Dashboard;


public class DashboardEgrowerGreenhouseCardFragment extends Fragment {
    private String owner;
    private String name;
    private String capacity;
    private String location;
    private TextView textOwner;
    private TextView textName;
    private TextView textCapacity;
    private TextView textLocation;
    private View rootView;
    private Button participateButton;

    public DashboardEgrowerGreenhouseCardFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerGreenhouseCardFragment newInstance(String owner, String name, String capacity, String location) {
        DashboardEgrowerGreenhouseCardFragment fragment = new DashboardEgrowerGreenhouseCardFragment();
        fragment.setOwner(owner);
        fragment.setName(name);
        fragment.setCapacity(capacity);
        fragment.setLocation(location);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_greenhouse_card, container, false);
        textOwner = rootView.findViewById(R.id.textView_owner_card_greenhouse_egrower);
        textName = rootView.findViewById(R.id.textView_name_card_greenhouse_egrower);
        textCapacity = rootView.findViewById(R.id.textView_capacity_card_greenhouse_egrower);
        textLocation = rootView.findViewById(R.id.textView_location_card_greenhouse_egrower);
        textOwner.setText(owner);
        textCapacity.setText(capacity);
        textLocation.setText(location);
        textName.setText(name);
        participateButton = rootView.findViewById(R.id.button_participate_greenhouse_card_egrower);
        participateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsorplantFormFragment.newInstance()).commit();
            }
        });
        return rootView;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}