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

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.MainActivity;

import java.util.List;


public class DashboardEgrowerConfirmSponsorFragment extends Fragment {
   private View rootView;
   private ImageView closeButton;
   private Button confirmButton;
   private Button backButton;
   private Integer plantsToSponsor;
   private Integer greenhouseID;
   private String greenhouseName;
   private TextView textViewPlantsToSponsor;
   private TextView textViewCostBca;
   public static final String Balance = "balanceKey";
   private DashboardEgrowerController dashboardEgrowerController;

    public DashboardEgrowerConfirmSponsorFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerConfirmSponsorFragment newInstance(Integer plants, Integer greenhouseID, String greenhouseName) {
        DashboardEgrowerConfirmSponsorFragment fragment = new DashboardEgrowerConfirmSponsorFragment();
        fragment.setGreenhouseID(greenhouseID);
        fragment.setPlantsToSponsor(plants);
        fragment.setGreenhouseName(greenhouseName);
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
        backButton = rootView.findViewById(R.id.button_back_confirm_form);
        Float totalCost = calculateCost(plantsToSponsor);
        textViewCostBca.setText(totalCost.toString());
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogConfirmSponsor(plantsToSponsor,totalCost);
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfUserHavePlants();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsorPlantFormFragment.newInstance(greenhouseName, greenhouseID),"SPONSOR_FORM").commit();
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
        String owner = sharedpreferences.getString("emailKey", "" );
        dashboardEgrowerController.sponsorPlant(this, plantsToSponsor, getGreenhouseID(), owner);
    }

    public void errorSponsoringPlant() {
        showDialog("Error!", "Error", false);
    }

    public void successSponsoringPlant(Integer plants, Float newBalance) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "" );
        String roll = sharedpreferences.getString("rollKey", "" );
        Integer avatar = sharedpreferences.getInt("avatarKey", 0 );
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(Balance,newBalance);
        editor.commit();
        showDialog("Success!", "you have been sponsored " + plants + " plants", false);
        checkIfUserHavePlants();
        updateGreenhouseCards();
        getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll, newBalance),"USER_INFORMATION").commit();
    }

    public void updateGreenhouseCards(){
        List<Greenhouse> greenhouses = dashboardEgrowerController.getAllGreenhouses(this);
        if(!greenhouses.isEmpty()){
            for (Greenhouse greenhouse: greenhouses) {
                String greenhouseOwner = greenhouse.getOwner();
                String name = greenhouse.getName();
                Integer capacity = greenhouse.getCapacity();
                String location = greenhouse.getLocation();
                Integer avatarIndex = greenhouse.getAvatar();
                Integer id = greenhouse.getId();
                Fragment fragment = getParentFragmentManager().findFragmentByTag("GH_CARD_"+id);
                getParentFragmentManager().beginTransaction().remove(fragment).commit();
                getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_horizontal_scroll, DashboardEgrowerGreenhouseCardFragment.newInstance(greenhouseOwner,name,id,capacity,location,avatarIndex), "GH_CARD_"+id).commit();
            }
        }
    }

    public void checkIfUserHavePlants(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String ownerEmail = sharedpreferences.getString("emailKey", "");
        List<Plant> plantsOwned = dashboardEgrowerController.getAllPlantsByOwner(this, ownerEmail);
        if (!plantsOwned.isEmpty()){
            Fragment fragment = getParentFragmentManager().findFragmentByTag("CONFIRM_FORM");
            getParentFragmentManager().beginTransaction().remove(fragment).commit();
            for (Plant plant: plantsOwned) {
                Integer quantity = plant.getQuantity();
                String greenhouseName = plant.getGreenhouse();
                Long plantId = plant.getId();
                getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredPlantsCardFragment.newInstance(quantity,greenhouseName,plantId,getGreenhouseID()), "PLANT_CARD_" + plant.getId()).commit();
            }
        } else if(plantsOwned.isEmpty()){
            getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredplantsEmptyFragment.newInstance()).commit();
        }
    }

    public void showDialog(String title, String msg, Boolean cancel){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        if(cancel){
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showDialogConfirmSponsor(Integer plants, Float cost){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        float actualBalance = sharedpreferences.getFloat("balanceKey", (float)0.0);
        float totalCostOfPlants = calculateCost(getPlantsToSponsor());
        if (actualBalance < totalCostOfPlants){
            showDialog("Warning!", "Insufficient balance, please recharge you account", true);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("You are going to sponsor "+ plants + " for " + cost + " BCA tokens")
                    .setTitle("Warning!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sponsorPlant();
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

    public String getGreenhouseName() {
        return greenhouseName;
    }

    public void setGreenhouseName(String greenhouseName) {
        this.greenhouseName = greenhouseName;
    }



}