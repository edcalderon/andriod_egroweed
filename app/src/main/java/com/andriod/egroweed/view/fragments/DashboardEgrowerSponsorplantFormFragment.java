package com.andriod.egroweed.view.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andriod.egroweed.R;

import java.util.Locale;

import es.dmoral.toasty.Toasty;


public class DashboardEgrowerSponsorplantFormFragment extends Fragment {

    private View rootView;
    private SeekBar seekBar;
    private String name;
    private TextView textViewSeekBar;
    private TextView greenHouseName;
    private Button seekBarButtonRemove;
    private Button seekBarButtonAdd;



    public DashboardEgrowerSponsorplantFormFragment() {
        // Required empty public constructor
    }

    public static DashboardEgrowerSponsorplantFormFragment newInstance(String name) {
        DashboardEgrowerSponsorplantFormFragment fragment = new DashboardEgrowerSponsorplantFormFragment();
        fragment.setName(name);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard_egrower_sponsorplant_form, container, false);
        seekBar = rootView.findViewById(R.id.seekBar_sponsorplats_form);
        seekBarButtonRemove = rootView.findViewById(R.id.button_seekbar_remove);
        seekBarButtonAdd = rootView.findViewById(R.id.button_seekbar_add);
        textViewSeekBar = rootView.findViewById(R.id.textView_seekbar_sporsorplants_form);
        greenHouseName = rootView.findViewById(R.id.textView_greenhouse_name_sponsor_form);
        greenHouseName.setText(getName().toUpperCase(Locale.ROOT));
        textViewSeekBar.setText(seekBar.getProgress()+ "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textViewSeekBar.setText(value + "/" + seekBar.getMax());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewSeekBar.setText(value + "/" + seekBar.getMax());
            }
        });
        seekBarButtonRemove.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                seekBar.setProgress(seekBar.getProgress() - 1, true);
                textViewSeekBar.setText(seekBar.getProgress() + "/" + seekBar.getMax());
            }
        });
        seekBarButtonAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                seekBar.setProgress(seekBar.getProgress() + 1, true);
                textViewSeekBar.setText(seekBar.getProgress() + "/" + seekBar.getMax());
            }
        });
        return rootView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}