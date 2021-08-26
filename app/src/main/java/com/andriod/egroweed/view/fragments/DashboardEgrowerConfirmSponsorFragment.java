package com.andriod.egroweed.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andriod.egroweed.R;


public class DashboardEgrowerConfirmSponsorFragment extends Fragment {
   private View rootView;
   private ImageView closeButton;

    public DashboardEgrowerConfirmSponsorFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerConfirmSponsorFragment newInstance() {
        DashboardEgrowerConfirmSponsorFragment fragment = new DashboardEgrowerConfirmSponsorFragment();
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
        closeButton = rootView.findViewById(R.id.imageView_close_button_confirm_sponsor_form);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.egrower_menu_linear_layout_vertical_scroll, DashboardEgrowerSponsoredplantsEmptyFragment.newInstance()).commit();
            }
        });

        return rootView;
    }
}