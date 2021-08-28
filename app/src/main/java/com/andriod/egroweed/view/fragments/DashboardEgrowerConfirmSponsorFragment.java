package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.Dashboard;
import com.andriod.egroweed.view.DashboardEgrowerMaster;
import com.andriod.egroweed.view.MainActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;


public class DashboardEgrowerConfirmSponsorFragment extends Fragment {
   private View rootView;
   private ImageView closeButton;
   private Button confirmButton;
   private Integer plantsToSponsor;
   private Integer greenhouseID;
   private TextView textViewPlantsToSponsor;
   private TextView textViewCostBca;
    public static final String Balance = "balanceKey";
   private DashboardEgrowerController dashboardEgrowerController;

    public DashboardEgrowerConfirmSponsorFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerConfirmSponsorFragment newInstance(Integer plants, Integer greenhouseID) {
        DashboardEgrowerConfirmSponsorFragment fragment = new DashboardEgrowerConfirmSponsorFragment();
        fragment.setGreenhouseID(greenhouseID);
        fragment.setPlantsToSponsor(plants);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_confirm_sponsor, container, false);
        textViewPlantsToSponsor = rootView.findViewById(R.id.textView_plants_to_sponsor_confirm_form);
        textViewCostBca = rootView.findViewById(R.id.textView_cost_bca_confirm_form);
        textViewPlantsToSponsor.setText(plantsToSponsor.toString());
        closeButton = rootView.findViewById(R.id.imageView_close_button_confirm_sponsor_form);
        confirmButton = rootView.findViewById(R.id.button_confirm_confirm_form);
        Float totalCost = calculateCost(plantsToSponsor);
        textViewCostBca.setText(totalCost.toString());
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sponsorPlant();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfUserHavePlants();
            }
        });
        dashboardEgrowerController = new DashboardEgrowerController();
        return rootView;
    }

    public float calculateCost(Integer plants){
        Float cost;
        cost = plants * (float) 300;
        return cost;
    }

    public void sponsorPlant(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        float actualBalance = sharedpreferences.getFloat("balanceKey", (float)0.0);
        String owner = sharedpreferences.getString("emailKey", "" );
        float totalCostOfPlants = calculateCost(this.plantsToSponsor);
        if (actualBalance < totalCostOfPlants){
            showDialog("Warning!", "Insufficient balance, please recharge you account");
        } else {
            dashboardEgrowerController.sponsorPlant(this, plantsToSponsor, getGreenhouseID(), owner);
        }
    }

    public void errorSponsoringPlant() {
        showDialog("Error!", "Error");
    }

    public void successSponsoringPlant(Integer plants, Float newBalance) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(Balance,newBalance);
        showDialog("Success!", "you have been sponsored " + plants + " plants");
        getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredPlantsFragment.newInstance(plants)).commit();
    }

    public void checkIfUserHavePlants(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String ownerEmail = sharedpreferences.getString("emailKey", "");
        List<Plant> plantsOwned = dashboardEgrowerController.getAllPlantsByOwnerInConfirmFragment(this, ownerEmail);
        if (!plantsOwned.isEmpty()){
            Fragment fragment = getParentFragmentManager().findFragmentByTag("CONFIRM_FORM");
            getParentFragmentManager().beginTransaction().remove(fragment).commit();
            for (Plant plant: plantsOwned) {
                Integer quantity = plant.getQuantity();
                getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredPlantsFragment.newInstance(quantity)).commit();
            }
        } else if(plantsOwned.isEmpty()){
            getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredplantsEmptyFragment.newInstance()).commit();
        }
    }

    public void showDialog(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public Integer getPlantsToSponsor() {
        return plantsToSponsor;
    }

    public void setPlantsToSponsor(Integer plantsToSponsor) {
        this.plantsToSponsor = plantsToSponsor;
    }

    public Integer getGreenhouseID() {
        return greenhouseID;
    }

    public void setGreenhouseID(Integer greenhouseID) {
        this.greenhouseID = greenhouseID;
    }



}