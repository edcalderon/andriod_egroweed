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
import android.widget.ImageView;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.ProfileController;
import com.andriod.egroweed.controller.ProfileFragmentController;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.view.MainActivity;

import es.dmoral.toasty.Toasty;


public class ProfileFragment extends Fragment {

    private EditText emailEditText;
    private EditText rollEditText;
    private EditText nameEditText;
    private ImageView avatarImageView;
    private Button updateButton;
    private View rootView;
    private String name;
    private String email;
    private String roll;
    private Integer avatar;
    private ProfileFragmentController profileFragmentController;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email =  getArguments().getString("email");
        name =  getArguments().getString("name");
        roll =  getArguments().getString("roll");
        avatar =  getArguments().getInt("avatar");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        emailEditText = rootView.findViewById(R.id.editText_profile_fragment_email);
        emailEditText.setText(email);
        emailEditText.setFocusable(false);
        nameEditText = rootView.findViewById(R.id.editText_profile_fragmente_name);
        nameEditText.setText(name);
        rollEditText = rootView.findViewById(R.id.editText_profile_fragment_roll);
        rollEditText.setText(roll);
        rollEditText.setFocusable(false);
        avatarImageView = rootView.findViewById(R.id.imageView_profile_fragment_avatar);
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
        updateButton = rootView.findViewById(R.id.button_profile_fragment_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
        profileFragmentController = new ProfileFragmentController();
        return rootView;
    }

    public void updateUser(){
        String email = emailEditText.getText().toString();
        String name = nameEditText.getText().toString();
        if(name.compareTo("")!=0){
            profileFragmentController.updateUser(this, email, name);
        } else {
            Toasty.warning(getActivity().getApplicationContext(), "Update Something", Toast.LENGTH_SHORT, true).show();
        }

    }

    public void updateUserSucceed(User user){
        String Name = "nameKey";
        String name = user.getName();
        nameEditText.setText(name);
        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, user.getName());
        editor.apply();
        Toasty.success(getActivity().getApplicationContext(), "User updated", Toast.LENGTH_SHORT, true).show();
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

    public void setRoll(String roll) {
        this.roll = roll;
    }
}