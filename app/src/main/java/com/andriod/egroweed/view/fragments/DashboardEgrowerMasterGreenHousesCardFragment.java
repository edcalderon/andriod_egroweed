package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.DashboardEgrowerMasterController;
import com.andriod.egroweed.model.pojo.Greenhouse;
import com.andriod.egroweed.model.pojo.Plant;
import com.andriod.egroweed.view.MainActivityRegister;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class DashboardEgrowerMasterGreenHousesCardFragment extends Fragment {
    private View rootView;
    private String owner;
    private String name;
    private Integer capacity;
    private String location;
    private Integer avatarIndex;
    private Integer greenhouseId;
    private EditText editTextOwner;
    private EditText editTextName;
    private EditText editTextCapacity;
    private EditText editTextLocation;
    private ImageView avatarImageView;
    private Button editButton;
    private Button deleteButton;
    private Button doneButton;
    private Button manageButton;
    private DashboardEgrowerMasterController dashboardEgrowerMasterController;

    public DashboardEgrowerMasterGreenHousesCardFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerMasterGreenHousesCardFragment newInstance(String owner, String name, Integer capacity, String location, Integer avatarIndex, Integer greenhouseId) {
        DashboardEgrowerMasterGreenHousesCardFragment fragment = new DashboardEgrowerMasterGreenHousesCardFragment();
        fragment.setOwner(owner);
        fragment.setName(name);
        fragment.setCapacity(capacity);
        fragment.setLocation(location);
        fragment.setAvatar(avatarIndex);
        fragment.setGreenhouseId(greenhouseId);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardEgrowerMasterController = new DashboardEgrowerMasterController();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master_green_card_houses, container, false);
        editTextOwner = rootView.findViewById(R.id.editText_owner_greenhouse);
        editTextName = rootView.findViewById(R.id.editText_greenhouse_card_name_egrower);
        editTextCapacity = rootView.findViewById(R.id.editText_capacity_greenhouse_card_egrower);
        editTextLocation = rootView.findViewById(R.id.editText_location_greenhouse_card_egrower);
        avatarImageView = rootView.findViewById(R.id.imageView_avatar_greenhouse_card_egrower);
        editButton = rootView.findViewById(R.id.button_edit_greenhouse);
        deleteButton = rootView.findViewById(R.id.button_delete_greenhouse_card);
        doneButton = rootView.findViewById(R.id.button_done_greenhouse_card);
        manageButton = rootView.findViewById(R.id.button_manage_greenhouse_master_card);
        deleteButton.setVisibility(View.GONE);
        doneButton.setVisibility(View.GONE);
        editTextOwner.setText(this.owner);
        editTextName.setText(name);
        editTextCapacity.setText(capacity.toString());
        editTextLocation.setText(location);
        editTextOwner.setFocusable(false);
        editTextName.setFocusable(false);
        editTextCapacity.setFocusable(false);
        editTextLocation.setFocusable(false);
        setAvatarImageView();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButton.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.VISIBLE);
                editButton.setVisibility(View.GONE);
                editTextName.setFocusableInTouchMode(true);
                editTextCapacity.setFocusableInTouchMode(true);
                editTextLocation.setFocusableInTouchMode(true);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGreenhouseDialog();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOld = name;
                String nameNew = editTextName.getText().toString();
                Integer capacity = getCapacity();
                String location = editTextLocation.getText().toString();
                updateGreenhouse(nameOld, nameNew, capacity,location);
            }
        });
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setManageView();
            }
        });
        return rootView;
    }

    public void setManageView(){
        DashboardEgrowerMasterController dashboardEgrowerMasterFragmentController = new DashboardEgrowerMasterController();
        List<Plant> plants = dashboardEgrowerMasterFragmentController.findPlantsByGreenhouse(this, getGreenhouseId());
        if(!plants.isEmpty()){
            getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, breadcrumbFragment.newInstance()).commit();
            for (Plant plant: plants){
                String owner = plant.getOwner();
                Integer quantity = plant.getQuantity();
                getParentFragmentManager().beginTransaction().add(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerMasterSponsoredPlantsFragment.newInstance(owner,quantity), "PLANT_CARD_MASTER_"+plant.getId()).commit();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("There is not sponsored plants in this greenhouse?")
                    .setTitle("Warning!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void updateGreenhouse(String nameOld, String nameNew, Integer capacity, String location){
        dashboardEgrowerMasterController.updateGreenhouse( this, nameOld, nameNew, capacity,location);
    }

    public void updateGreenhouseSucceed(Greenhouse greenhouse) {
        Toasty.success(getContext(),  greenhouse.getName()+ " Updated!", Toast.LENGTH_SHORT, true).show();
        editTextName.setFocusable(false);
        editTextCapacity.setFocusable(false);
        editTextLocation.setFocusable(false);
        deleteButton.setVisibility(View.GONE);
        doneButton.setVisibility(View.GONE);
        editButton.setVisibility(View.VISIBLE);
    }

    public void deleteGreenhouseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure to delete this Greenhouse?")
                .setTitle("Warning!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteGreenhouse();
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
    private void deleteGreenhouse(){
        dashboardEgrowerMasterController.deleteGreenhouseByName( this, name);
    }

    public void deleteGreenhouseSucceed(Greenhouse greenhouse){
        Toasty.error(getContext(), owner + " " + greenhouse.getName()+ " Deleted!", Toast.LENGTH_SHORT, true).show();
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivityRegister.SESSION, Context.MODE_PRIVATE);
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
        deleteButton.setVisibility(View.GONE);
        doneButton.setVisibility(View.GONE);
        editButton.setVisibility(View.VISIBLE);
    }

    public void setAvatarImageView(){
        switch (avatarIndex){
            case 0:
                avatarImageView.setImageResource(R.drawable.ic_green_house_1);
                break;
            case 1:
                avatarImageView.setImageResource(R.drawable.ic_green_house_2);
                break;
            case 2:
                avatarImageView.setImageResource(R.drawable.ic_green_house_3);
                break;
            case 3:
                avatarImageView.setImageResource(R.drawable.ic_green_house_4);
                break;
            case 4:
                avatarImageView.setImageResource(R.drawable.ic_green_house_5);
                break;
        }
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAvatar() {
        return avatarIndex;
    }

    public void setAvatar(Integer avatar) {
        this.avatarIndex = avatar;
    }

    public Integer getGreenhouseId() {
        return greenhouseId;
    }

    public void setGreenhouseId(Integer greenhouseId) {
        this.greenhouseId = greenhouseId;
    }


}