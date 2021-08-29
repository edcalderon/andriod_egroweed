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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.MainActivity;

import java.util.List;
import java.util.Locale;


public class DashboardEgrowerSponsoredPlantsCardFragment extends Fragment {
    private View rootView;
    private TextView textViewSponsoredPlants;
    private TextView textViewGreenhouseName;
    private Integer numberOfSponsoredPlants;
    private Integer daysLeftCloning = 5;
    private Integer daysLeftGrowing= 45;
    private Integer daysLeftFlowering= 45;
    private Integer daysLeftHarvesting= 5;
    private ImageView imageViewArrow1;
    private ImageView imageViewArrow2;
    private ImageView imageViewArrow3;
    private ImageView imageViewCloning;
    private ImageView imageViewGrowing;
    private ImageView imageViewFlowering;
    private ImageView imageViewHarvesting;
    private ImageView imageViewEarlySold;
    private TextView textViewDaysLeftCloning;
    private TextView textViewDaysLeftGrowing;
    private TextView textViewDaysLeftFlowering;
    private TextView textViewDaysLeftHarvesting;
    private String greenhouseName;
    private Integer greenhouseId;
    private long plantId;
    private ProgressBar cloningProgressBar;
    private ProgressBar growingProgressBar;
    private ProgressBar floweringProgressBar;
    private ProgressBar harvestingProgressBar;
    private DashboardEgrowerController dashboardEgrowerController;

    public DashboardEgrowerSponsoredPlantsCardFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerSponsoredPlantsCardFragment newInstance(Integer sponsoredPlants, String greenhouseName, Long plantId, Integer greenhouseId) {
        DashboardEgrowerSponsoredPlantsCardFragment fragment = new DashboardEgrowerSponsoredPlantsCardFragment();
        fragment.setNumberOfSponsoredPlants(sponsoredPlants);
        fragment.setGreenhouseName(greenhouseName);
        fragment.setPlantId(plantId);
        fragment.setGreenhouseId(greenhouseId);
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
        textViewSponsoredPlants = rootView.findViewById(R.id.textView_sponsored_plants);
        textViewGreenhouseName = rootView.findViewById(R.id.textView_greenhouseName_sponsored_card);
        cloningProgressBar = rootView.findViewById(R.id.progressBar_cloning_sponsored_card);
        growingProgressBar = rootView.findViewById(R.id.progressBar_growing_sponsored_card);
        floweringProgressBar = rootView.findViewById(R.id.progressBar_flowering_sponsored_card);
        harvestingProgressBar = rootView.findViewById(R.id.progressBar_harvesting_sponsored_card);
        textViewDaysLeftCloning = rootView.findViewById(R.id.textView_days_left_cloning_sponsored_card);
        textViewDaysLeftGrowing = rootView.findViewById(R.id.textView_days_left_growing_sponsored_card);
        textViewDaysLeftFlowering = rootView.findViewById(R.id.textView_days_left_flowering_sponsored_card);
        textViewDaysLeftHarvesting= rootView.findViewById(R.id.textView_days_left_harvesting_sponsored_card);
        imageViewCloning = rootView.findViewById(R.id.imageView_cloning_sponsored_card);
        imageViewGrowing = rootView.findViewById(R.id.imageView_growing_sponsored_card);
        imageViewFlowering = rootView.findViewById(R.id.imageView_flowering_sponsered_card);
        imageViewHarvesting = rootView.findViewById(R.id.imageView_harvesting_sponsored_card);
        imageViewEarlySold = rootView.findViewById(R.id.imageView_early_sold_sponsored_card);
        imageViewArrow1 = rootView.findViewById(R.id.imageView_arrow_1_sponsored_card);
        imageViewArrow2 = rootView.findViewById(R.id.imageView_arrow_2_sponsored_card);
        imageViewArrow3 = rootView.findViewById(R.id.imageView_arrow_3_sponsored_card);
        imageViewArrow1.setVisibility(View.GONE);
        imageViewArrow2.setVisibility(View.GONE);
        imageViewArrow3.setVisibility(View.GONE);
        textViewDaysLeftCloning.setText(daysLeftCloning.toString() + " days left");
        textViewDaysLeftGrowing.setText(daysLeftGrowing.toString() + " days left");
        textViewDaysLeftFlowering.setText((daysLeftFlowering.toString() + "days left"));
        textViewDaysLeftHarvesting.setText((daysLeftHarvesting.toString() + "days left"));
        textViewGreenhouseName.setText(getGreenhouseName().toUpperCase(Locale.ROOT));
        textViewSponsoredPlants.setText(getNumberOfSponsoredPlants().toString());
        cloningProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysLeftCloning > 0){
                    setProgress(cloningProgressBar,textViewDaysLeftCloning,imageViewCloning,daysLeftCloning, imageViewArrow1);
                    daysLeftCloning = daysLeftCloning - 1;
                }
            }
        });
        growingProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysLeftCloning==0 && daysLeftGrowing>0){
                    setProgress(growingProgressBar,textViewDaysLeftGrowing,imageViewGrowing,daysLeftGrowing, imageViewArrow2);
                    daysLeftGrowing = daysLeftGrowing - 1;
                }
            }
        });
        floweringProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysLeftCloning==0 && daysLeftGrowing==0 && daysLeftFlowering>0){
                    setProgress(floweringProgressBar,textViewDaysLeftFlowering,imageViewFlowering,daysLeftFlowering, imageViewArrow3);
                    daysLeftFlowering = daysLeftFlowering - 1;
                } if( daysLeftFlowering > daysLeftHarvesting/2) {
                    imageViewEarlySold.setVisibility(View.GONE);
                }
            }
        });
        harvestingProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daysLeftCloning==0 && daysLeftGrowing==0 && daysLeftFlowering ==0 && daysLeftHarvesting>0){
                    setProgress(harvestingProgressBar,textViewDaysLeftHarvesting,imageViewHarvesting,daysLeftHarvesting, imageViewArrow3);
                    daysLeftHarvesting = daysLeftHarvesting - 1;
                } else {
                    sellCrop(getPlantId());
                }
            }
        });
        if(daysLeftCloning == 0){
            imageViewArrow1.setVisibility(View.VISIBLE);
        }
        if(daysLeftGrowing == 0){
            imageViewArrow2.setVisibility(View.VISIBLE);
        }
        if(daysLeftFlowering == 0){
            imageViewArrow3.setVisibility(View.VISIBLE);
        }
        if(daysLeftHarvesting == 0){
            sellCrop(getPlantId());
        }
        imageViewEarlySold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEarlySoldDialog();
            }
        });
        dashboardEgrowerController = new DashboardEgrowerController();
        return rootView;
    }

    private void setProgress(ProgressBar progressBar, TextView textView, ImageView imageView, Integer daysLeft, ImageView imageViewArrow){
        if(daysLeft>1){
            daysLeft = daysLeft - 1;
            textView.setText(daysLeft.toString() + " Days left");
            progressBar.setProgress(progressBar.getProgress()+(100/daysLeft));
        } else {
            imageView.setImageResource(R.drawable.ic_check);
            textView.setText("succeed");
            imageViewArrow.setVisibility(View.VISIBLE);
        }
    }

    private void sellCrop(Long plantId){
        Fragment fragment = getParentFragmentManager().findFragmentByTag("PLANT_CARD_"+plantId);
        getParentFragmentManager().beginTransaction().remove(fragment).commit();
        getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredCardSucceedFragment.newInstance(getNumberOfSponsoredPlants(), getPlantId()), "SUCCEED_CARD_" + plantId).commit();
    }

    private void showEarlySoldDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure to early sold your plants? We will return you the initial cost of plants.")
                .setTitle("Warning!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        earlySellPlant(getPlantId(), getGreenhouseId());
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

    private void earlySellPlant(Long plantId, Integer greenhouseId){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String ownerEmail = sharedpreferences.getString("emailKey", "");
        Plant plant = dashboardEgrowerController.getPlantById(this, plantId);
        dashboardEgrowerController.earlySellPlant(this, plant,ownerEmail, greenhouseId);
    }

    public void earlySoldPlantSucceed(Plant plant, Float newBalance){
        Fragment fragment = getParentFragmentManager().findFragmentByTag("PLANT_CARD_"+plant.getId());
        getParentFragmentManager().beginTransaction().remove(fragment).commit();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        updateBalance(newBalance);
        updateGreenhouseCards();
        builder.setMessage("You have been sold " + plant.getQuantity() + " plants of the green house " + plant.getGreenhouse())
                .setTitle("Success!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateBalance(Float newBalance){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "" );
        String roll = sharedpreferences.getString("rollKey", "" );
        Integer avatar = sharedpreferences.getInt("avatarKey", 0 );
        String Balance = "balanceKey";
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(Balance,newBalance);
        editor.commit();
        getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_user_information_fragment_dashboard, DashboardUserInformationFragment.newInstance(name, avatar, roll, newBalance),"USER_INFORMATION").commit();
    }

    private void updateGreenhouseCards(){
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

    public Integer getNumberOfSponsoredPlants() {
        return numberOfSponsoredPlants;
    }

    public void setNumberOfSponsoredPlants(Integer numberOfSponsoredPlants) {
        this.numberOfSponsoredPlants = numberOfSponsoredPlants;
    }
    public String getGreenhouseName() {
        return greenhouseName;
    }

    public void setGreenhouseName(String greenhouseName) {
        this.greenhouseName = greenhouseName;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public Integer getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Integer greenhouseId) {
        this.greenhouseId = greenhouseId;
    }
}