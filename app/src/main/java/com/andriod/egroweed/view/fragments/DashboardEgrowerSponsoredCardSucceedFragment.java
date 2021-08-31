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
import android.widget.TextView;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.MainActivityRegister;

import java.util.List;
import java.util.Locale;


public class DashboardEgrowerSponsoredCardSucceedFragment extends Fragment {

    private View rootView;
    private TextView textViewCroppedPlants;
    private TextView textViewGreenhouseName;
    private Button buttonSellCrop;
    private Integer croppedPlants;
    private Long plantId;
    private String greenhouseName;
    private DashboardEgrowerController dashboardEgrowerController;


    public DashboardEgrowerSponsoredCardSucceedFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerSponsoredCardSucceedFragment newInstance(Integer croppedPlants, Long plantId, String greenhouseName) {
        DashboardEgrowerSponsoredCardSucceedFragment fragment = new DashboardEgrowerSponsoredCardSucceedFragment();
        fragment.setCroppedPlants(croppedPlants);
        fragment.setPlantId(plantId);
        fragment.setGreenhouseName((greenhouseName));
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_sponsored_card_succeed, container, false);
        textViewCroppedPlants = rootView.findViewById(R.id.textView_croped_plants_sponsored_succeed_card);
        textViewGreenhouseName = rootView.findViewById(R.id.textView_greenhouse_sponsored_succeed_card);
        textViewCroppedPlants.setText(getCroppedPlants() + " Plants cropped are ready to sell");
        textViewGreenhouseName.setText(getGreenhouseName().toUpperCase(Locale.ROOT));
        buttonSellCrop = rootView.findViewById(R.id.button_sell_crop_sponsored_succeed_card);
        dashboardEgrowerController = new DashboardEgrowerController();
        buttonSellCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellCrop();
            }
        });
        return rootView;
    }

    private void sellCrop(){
        dashboardEgrowerController.sellCrop(this,getPlantId());
    }

    public void sellCropSucceed(Plant plant, float newBalance, float earnings) {
        Fragment fragment = getParentFragmentManager().findFragmentByTag("SUCCEED_CARD_"+plant.getId());
        getParentFragmentManager().beginTransaction().remove(fragment).commit();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        updateBalance(newBalance);
        updateGreenhouseCards();
        builder.setMessage("We return you " + plant.getPrice() + " + " + earnings + " BCA Tokens in earnings")
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
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivityRegister.SESSION, Context.MODE_PRIVATE);
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

    public Integer getCroppedPlants() {
        return croppedPlants;
    }

    public void setCroppedPlants(Integer croppedPlants) {
        this.croppedPlants = croppedPlants;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getGreenhouseName() {
        return greenhouseName;
    }

    public void setGreenhouseName(String greenhouseName) {
        this.greenhouseName = greenhouseName;
    }


}