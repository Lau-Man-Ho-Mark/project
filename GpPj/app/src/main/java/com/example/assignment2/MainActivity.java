package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
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

    private Fragment home, calorie, progress;
    private BottomNavigationView bottomView;
    public static boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cleanSharedPref();
        home = new HomeFragment();
        calorie = new CalorieFragment();
        progress = new ProgressFragment();
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

    private void cleanSharedPref() {
        if(isFirstTime){
            SharedPreferences preferences = getSharedPreferences(CalorieFragment.mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();

            preferences = getSharedPreferences(SportInstruction.SHARED_PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor2 = preferences.edit();
            editor2.clear();

            editor.commit();
            editor2.commit();
            isFirstTime = false;
        }

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
                getSupportFragmentManager().
                        beginTransaction().
                    replace(R.id.frameLayout, calorie).commit();
                break;

            case R.id.proFrag:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.frameLayout, progress).commit();
                break;

        }
    }


}