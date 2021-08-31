package com.andriod.egroweed.view.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.WalletFragmentController;
import com.andriod.egroweed.view.MainActivityRegister;

import es.dmoral.toasty.Toasty;


public class WalletFragment extends Fragment {
    private View rootView;
    private TextView balance;
    private CardView receiveBalance;
    private String ownerEmail;
    private WalletFragmentController walletFragmentController;
    public static final String Balance = "balanceKey";


    public WalletFragment() {
        // Required empty public constructor
    }

    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivityRegister.SESSION, Context.MODE_PRIVATE);
        rootView = inflater.inflate(R.layout.fragment_wallet, container, false);
        balance = rootView.findViewById(R.id.textView_user_balance_wallet);
        receiveBalance = rootView.findViewById(R.id.cardView_receive_balance);
        float userBalance = sharedpreferences.getFloat("balanceKey", (float)0.0);
        ownerEmail = sharedpreferences.getString("emailKey", "");
        balance.setText(String.format("%.2f", userBalance));
        receiveBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserBalance();
            }
        });
        walletFragmentController = new WalletFragmentController();
        return rootView;
    }

    public void updateUserBalance(){
        walletFragmentController.addUserBalance(this, (float) 10000, ownerEmail);
    }

    public void updateUserBalanceSucceed(Float newBalance) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivityRegister.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(Balance,newBalance);
        editor.commit();
        balance.setText(String.valueOf(newBalance));
        Toasty.success(getContext(), "You have been receive 10.000 BCA Tokens", Toast.LENGTH_SHORT, true).show();
    }
}