package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andriod.egroweed.R;
import com.andriod.egroweed.view.Dashboard;

import java.util.Locale;


public class DashboardEgrowerGreenhouseCardFragment extends Fragment {
    private String owner;
    private String name;
    private String capacity;
    private String location;
    private Integer avatarIndex;
    private TextView textOwner;
    private TextView textName;
    private TextView textCapacity;
    private TextView textLocation;
    private ImageView avatarImageView;
    private View rootView;
    private Button participateButton;

    public DashboardEgrowerGreenhouseCardFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerGreenhouseCardFragment newInstance(String owner, String name, String capacity, String location, Integer avatarIndex) {
        DashboardEgrowerGreenhouseCardFragment fragment = new DashboardEgrowerGreenhouseCardFragment();
        fragment.setOwner(owner);
        fragment.setName(name);
        fragment.setCapacity(capacity);
        fragment.setLocation(location);
        fragment.setAvatar(avatarIndex);
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
        avatarImageView = rootView.findViewById(R.id.imageView_avatar_greenhouse_card_egrower);
        textOwner.setText(owner);
        textCapacity.setText(capacity);
        textLocation.setText(location);
        textName.setText(name.toUpperCase(Locale.ROOT));
        participateButton = rootView.findViewById(R.id.button_participate_greenhouse_card_egrower);
        participateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsorplantFormFragment.newInstance(name)).commit();
            }
        });
        setAvatarImageView();
        return rootView;
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

    public Integer getAvatar() {
        return avatarIndex;
    }

    public void setAvatar(Integer avatar) {
        this.avatarIndex = avatar;
    }
}