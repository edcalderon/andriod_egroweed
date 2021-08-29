package com.andriod.egroweed.view.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.ProfileFragmentController;
import com.andriod.egroweed.controller.WalletFragmentController;
import com.andriod.egroweed.view.MainActivity;


public class WalletFragment extends Fragment {
    private View rootView;
    private ImageButton logoutButton;
    private TextView balance;
    private EditText inputBalance;
    private Button setButton;
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
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        rootView = inflater.inflate(R.layout.fragment_wallet, container, false);
        balance = rootView.findViewById(R.id.textView_user_balance_wallet);
        setButton = rootView.findViewById(R.id.button_set_balance);
        inputBalance = rootView.findViewById(R.id.editTextNumber_set_balance);
        float userBalance = sharedpreferences.getFloat("balanceKey", (float)0.0);
        ownerEmail = sharedpreferences.getString("emailKey", "");
        balance.setText(String.valueOf(userBalance));
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserBalance();
            }
        });
        walletFragmentController = new WalletFragmentController();
        return rootView;
    }

    public void updateUserBalance(){
        walletFragmentController.updateUserBalance(this, Float.parseFloat(inputBalance.getText().toString()), ownerEmail);
    }

    public void updateUserBalanceSucceed(Float newBalance) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat(Balance,newBalance);
        editor.commit();
        balance.setText(String.valueOf(newBalance));
    }
}