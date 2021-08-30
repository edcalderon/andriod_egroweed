package com.andriod.egroweed.view.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.andriod.egroweed.R;

public class DashboardEgrowerMasterSponsoredPlantsFragment extends Fragment {
    private String owner;
    private Integer quantity;
    private View rootView;
    private TextView textViewOwner;
    private TextView textViewQuantity;
    private Button sowButton;
    private Button cropButton;
    private Button sellButton;

    public DashboardEgrowerMasterSponsoredPlantsFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerMasterSponsoredPlantsFragment newInstance(String owner, Integer quantity) {
        DashboardEgrowerMasterSponsoredPlantsFragment fragment = new DashboardEgrowerMasterSponsoredPlantsFragment();
        fragment.setOwner(owner);
        fragment.setQuantity(quantity);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_master_sponsored_plants, container, false);
        textViewOwner = rootView.findViewById(R.id.textView_sponsored_by_greenhouse_master_card);
        textViewQuantity = rootView.findViewById(R.id.textView_sponsored_quantity_greenhouse_master_card);
        textViewOwner.setText(getOwner());
        textViewQuantity.setText(getQuantity().toString());
        sowButton = rootView.findViewById(R.id.button_sow_);
        cropButton = rootView.findViewById(R.id.button_crop_);
        sellButton = rootView.findViewById(R.id.button_sell_);
        sowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure to sow this plants?")
                        .setTitle("Warning!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sowPlants();
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
        });
        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure crop to this plants?")
                        .setTitle("Warning!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cropPlants();
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
        });
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure to sell this plants?")
                        .setTitle("Warning!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sellPlants();
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
        });
        sowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure sow this plants?")
                        .setTitle("Warning!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sowPlants();
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
        });
        return  rootView;
    }

    private void sowPlants(){
        sowPlantsSucceed();
    }

    private void sowPlantsSucceed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Plants sowed")
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

    private void cropPlants(){
      cropPlantsSucceed();;
    }

    private void cropPlantsSucceed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Plants sowed")
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

    private void sellPlants(){
        sellPlantsSucceed();;
    }

    private void sellPlantsSucceed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Plants sowed")
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}