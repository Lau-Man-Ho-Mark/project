package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity /*implements HomeFragment.passDataListener*/ {

    static Locale locale1;
   private Fragment home, calorie, progress, setting;
   private BottomNavigationView bottomView;


    Bundle bd;
    boolean isChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        isChecked = i.getBooleanExtra("isChecked", false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
               // passData(2);
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

    private void passData(int i) {
            switch (i){
                case 1:
                    calorie.setArguments(bd);
                    break;

                case 2:
                    progress.setArguments(bd);
                    break;
            }


    }



    /*
        Interface in Home, update when needed
    @Override
    public void passData(String name, String height, String weight, int gender, boolean isChecked) {
            setLanguage(isChecked);
            //wrapData();

    }
    */


    /* Reuse in Setting when needed
    private void setLanguage(boolean isChecked) {
        if(!isChecked){
            locale1 = new Locale("en_US");
            changeLocale(locale1, isChecked);

        }
        else{
            locale1 = new Locale("zh","HK");
            changeLocale(locale1, isChecked);
        }
    }
    private void changeLocale(Locale locale, Boolean isChecked){

        //Resetting the configuration
        //And resetting the locale to be trad chinese
        locale.setDefault(locale);
        Resources resources = this.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());

        //Detach and attach the fragment for refreshing the fragment
        //the if else statement assures this trick work for the new api levels
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().detach(home).commitNow();
        fragmentManager.beginTransaction().attach(home).commitNow();


    }*/

    /*
    //date picker method
    public void datePicker(View v){
        View view = this.getCurrentFocus();
        if(view != null){
            Log.d("Success", "Closing keyboard");
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        EditText dateET = (EditText) findViewById(R.id.et4);
        DatePickerDialog picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        forDay = day;
                        forMonth = month;
                        forYear = year;
                        dateET.setText(date);
                        Log.d("Success", "Date is successfully picked");
                    }
                }, year, month, day);
                picker.show();
    }*/

}