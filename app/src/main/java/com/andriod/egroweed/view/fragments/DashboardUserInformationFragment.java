package com.andriod.egroweed.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.andriod.egroweed.R;

public class DashboardUserInformationFragment extends Fragment {
    private String name;
    private String email;
    private Integer avatar;
    private String roll;
    private float balance;
    private View rootView;
    private TextView emailTextView;
    private ImageView avatarImageView;
    private TextView rollTextView;
    private TextView balanceTextView;

    public DashboardUserInformationFragment() {
        // Required empty public constructor
    }

    public static DashboardUserInformationFragment newInstance(String name, Integer avatar, String roll, Float balance) {
        DashboardUserInformationFragment fragment = new DashboardUserInformationFragment();
        fragment.setEmail(name);
        fragment.setName(name);
        fragment.setAvatar(avatar);
        fragment.setRoll(roll);
        fragment.setBalance(balance);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_information, container, false);
        emailTextView = rootView.findViewById(R.id.user_email_user_information_fragment);
        avatarImageView = rootView.findViewById(R.id.avatar_information_fragment);
        rollTextView = rootView.findViewById(R.id.textView_roll_user_fragment2);
        balanceTextView = rootView.findViewById(R.id.textView_bca_balance_user_information_fragment);
        emailTextView.setText(name);
        rollTextView.setText(getRoll());
        balanceTextView.setText(String.valueOf(getBalance()));

        switch (avatar){
            case 0:
                avatarImageView.setImageResource(R.drawable.ic_avatar_1);
                break;
            case 1:
                avatarImageView.setImageResource(R.drawable.ic_avatar_2);
                break;
            case 2:
                avatarImageView.setImageResource(R.drawable.ic_avatar_3);
                break;
            case 3:
                avatarImageView.setImageResource(R.drawable.ic_avatar_4);
                break;
            case 4:
                avatarImageView.setImageResource(R.drawable.ic_avatar_5);
                break;
        }
        return  rootView;
    }

    public String getName() {
        return name;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}