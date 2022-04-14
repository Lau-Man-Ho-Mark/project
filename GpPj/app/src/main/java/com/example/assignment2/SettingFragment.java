package com.example.assignment2;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    Switch switchy, switchy2;
    Locale locale;

    public interface settingData{
        public void settingData(boolean chineseMode, boolean darkMode);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (settingData) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }


    settingData listener;


    public static final String SETTINGPREFERENCE = "settingPreference";
    public static final String DARKMODE = "darkMode";
    public static final String CHINESE = "chinese";
    SharedPreferences preferences;
    boolean forChinese, forDarkMode;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        init(v);
        preferences = getActivity().getSharedPreferences(SETTINGPREFERENCE, Context.MODE_PRIVATE);
        forChinese = preferences.getBoolean(CHINESE, false);
        forDarkMode = preferences.getBoolean(DARKMODE, false);
        setData();

        listener.settingData(forChinese, forDarkMode);



        // Inflate the layout for this fragment
        return v;
    }


    private void setData() {

        switchy.setChecked(forChinese);
        switchy2.setChecked(forDarkMode);
    }

    private void init(View v) {
        switchy = v.findViewById(R.id.switchy);
        switchy2 = v.findViewById(R.id.darkMode);

        switchy.setOnCheckedChangeListener(this);
        switchy2.setOnCheckedChangeListener(this);
    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch(compoundButton.getId()){
            case R.id.switchy:
                SharedPreferences.Editor editor = preferences.edit();
                forChinese = b;
                editor.putBoolean(CHINESE, b);
                editor.commit();
                //System.out.println(preferences.getBoolean(CHINESE, true));
                break;

            case R.id.darkMode:
                editor = preferences.edit();
                forDarkMode = b;
                editor.putBoolean(DARKMODE, b);
                editor.commit();
                //System.out.println(preferences.getBoolean(DARKMODE, true));
                break;
        }


        listener.settingData(forChinese, forDarkMode);

    }
}