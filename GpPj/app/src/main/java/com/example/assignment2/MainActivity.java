package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity{

    static Locale locale1;
    Intent i;
    private Fragment home, calorie, progress, setting;
    private BottomNavigationView bottomView;
    Bundle bd;

    ArrayList<String> repList = new ArrayList<>();
    ArrayList<String> caloList = new ArrayList<>();
    ArrayList<String> sportTypeList = new ArrayList<>();

    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = getIntent();
        repList =  i.getStringArrayListExtra("repSecList");
        caloList =  i.getStringArrayListExtra("calorList");
        sportTypeList = i.getStringArrayListExtra("sportstypeList");



        home = new HomeFragment();
        calorie = new CalorieFragment();
        progress = new ProgressFragment();
        setting = new SettingFragment();

        changeFragment(R.id.homeFrag);
        //Home fragment is always the first page before the bottom navigation items are being selected


        //finding the matched bottomNavigation view
        bottomView = findViewById(R.id.bottomNav);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Calling a defined method to handle the fragment transitions
                changeFragment(item.getItemId());
                return true;
            }
        });

    }

    //change fragment method
    public void changeFragment(int itemId) {

        switch (itemId){
            case R.id.homeFrag:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.frameLayout, home).commit();
            break;

           case R.id.calorieFrag:
                //passData(1);
                getSupportFragmentManager().
                        beginTransaction().
                    replace(R.id.frameLayout, calorie).commit();
                break;

            case R.id.proFrag:
               passData();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.frameLayout, progress).commit();
                break;

            case R.id.setFrag:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.frameLayout, setting).commit();
                break;

        }
    }

    private void passData() {

        bd = new Bundle();
        Bundle fragABundle = new Bundle();
        fragABundle.putStringArrayList("repList", repList);
        fragABundle.putStringArrayList("burntCaloriesList", caloList);
        fragABundle.putStringArrayList("sportstypeList", sportTypeList);
        bd.putBundle("fragA", fragABundle);

        progress.setArguments(bd);

    }



}