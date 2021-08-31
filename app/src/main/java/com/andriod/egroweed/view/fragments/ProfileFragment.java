package com.andriod.egroweed.view.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andriod.egroweed.R;
import com.andriod.egroweed.controller.ProfileFragmentController;
import com.andriod.egroweed.model.pojo.User;
import com.andriod.egroweed.view.MainActivity;

import es.dmoral.toasty.Toasty;


public class ProfileFragment extends Fragment {

    private EditText emailEditText;
    private EditText rollEditText;
    private EditText nameEditText;
    private TextView usernameTextView;
    private ImageView avatarImageView;
    private ImageView logOutImageView;
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
        usernameTextView = rootView.findViewById(R.id.textView_username_profile);
        usernameTextView.setText(email.substring(0,email.indexOf("@")));
        nameEditText = rootView.findViewById(R.id.editText_profile_fragmente_name);
        nameEditText.setText(name);
        rollEditText = rootView.findViewById(R.id.editText_profile_fragment_roll);
        rollEditText.setText(roll);
        rollEditText.setFocusable(false);
        avatarImageView = rootView.findViewById(R.id.imageView_profile_fragment_avatar);
        setAvatarImageView(avatar);
        updateButton = rootView.findViewById(R.id.button_profile_fragment_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
        logOutImageView = rootView.findViewById(R.id.imageView_logout_profile_fragment);
        logOutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
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

    public void logout(){
        AlertDialog.Builder builder_logout = new AlertDialog.Builder(this.getContext())
                .setTitle("Exit")
                .setMessage("Do you want to exit?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder_logout.create().show();
    }

    public void setAvatarImageView(Integer avatarIndex){
        switch (avatarIndex){
            case 0:
                avatarImageView.setImageResource(R.drawable.ic_avatar_1);
                break;
            case 1:
                avatarImageView.setImageResource(R.drawable.ic_avatar_2);
                break;
            case 2:
                avatarImageView.setImageResource(R.drawable.ic_avatar_3_);
                break;
            case 3:
                avatarImageView.setImageResource(R.drawable.ic_avatar_4);
                break;
            case 4:
                avatarImageView.setImageResource(R.drawable.ic_avatar_5);
                break;
            case 5:
                avatarImageView.setImageResource(R.drawable.ic_avatar_6);
                break;
            case 6:
                avatarImageView.setImageResource(R.drawable.ic_avatar_7);
                break;
            case 7:
                avatarImageView.setImageResource(R.drawable.ic_avatar_8);
                break;
            case 8:
                avatarImageView.setImageResource(R.drawable.ic_avatar_9);
                break;
            case 9:
                avatarImageView.setImageResource(R.drawable.ic_avatar_10);
                break;
        }
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