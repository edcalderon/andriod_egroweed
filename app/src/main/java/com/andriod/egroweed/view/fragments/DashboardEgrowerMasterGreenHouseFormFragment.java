package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerMasterController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.view.DashboardEgrowerMaster;
import com.andriod.egroweed.view.MainActivity;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.dmoral.toasty.Toasty;

public class DashboardEgrowerMasterGreenHouseFormFragment extends Fragment {

    private View rootView;
    private Button createButton;
    private FloatingActionButton cancelButton;
    private FloatingActionsMenu FaMenu;
    private EditText nameEditText;
    private EditText capacityEditText;
    private EditText locationEditText;
    private DashboardEgrowerMasterController dashboardEgrowerMasterController;
    private String owner;

    public DashboardEgrowerMasterGreenHouseFormFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerMasterGreenHouseFormFragment newInstance(String email) {
        DashboardEgrowerMasterGreenHouseFormFragment fragment = new DashboardEgrowerMasterGreenHouseFormFragment();
        fragment.setOwner(email);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        dashboardEgrowerMasterController = new DashboardEgrowerMasterController();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master_green_house_form, container, false);

        nameEditText = rootView.findViewById(R.id.editTex_name_green_house_form);
        capacityEditText = rootView.findViewById(R.id.editTex_capacity_green_house_form);
        locationEditText = rootView.findViewById(R.id.editText_location_green_form);

        createButton = rootView.findViewById(R.id.button_create_greenhouse_form);
        cancelButton = rootView.findViewById(R.id.floatingActionButton_cancel_greenhouse_form);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String capacity = capacityEditText.getText().toString();
                String location = locationEditText.getText().toString();
                String owner = getOwner();
                if(!name.isEmpty() && !capacity.isEmpty() && !location.isEmpty() && !owner.isEmpty()){
                    createGreenhouse(name, capacity, location, owner);
                } else {
                    Toasty.error(getActivity().getApplicationContext(),  "All fields are required", Toast.LENGTH_SHORT, true).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToDashboard();
            }
        });
        return rootView;
    }
    public void createGreenhouse(String name, String capacity, String location, String owner){
        dashboardEgrowerMasterController.createGreenHouse(this, name, capacity, location, owner);
    }
    public void createSucceed(Greenhouse greenhouse){
        String name = greenhouse.getName();
        Toasty.success(getActivity().getApplicationContext(), name + " Created!", Toast.LENGTH_SHORT, true).show();
        returnToDashboard();
    }

    public void returnToDashboard(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userRoll = getActivity().getIntent().getExtras().getString("userRoll") != null ? getActivity().getIntent().getExtras().getString("userRoll") : "";
        int userAvatar = getActivity().getIntent().getExtras().getInt("userAvatar") != -1 ? getActivity().getIntent().getExtras().getInt("userAvatar") : 0;

        Bundle bundle = new Bundle();
        bundle.putString("name", sessionName);
        bundle.putString("email", sessionEmail);
        bundle.putString("owner", sessionEmail);
        bundle.putString("roll", userRoll);
        bundle.putInt("avatar", userAvatar);

        DashboardEgrowerMasterFragment fragment = new DashboardEgrowerMasterFragment();

        fragment.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_dashboard, fragment);
        transaction.commit();

    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}