package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressFragment extends Fragment{



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZodiacFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressFragment newInstance(String param1, String param2) {
        ProgressFragment fragment = new ProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    TextView display_sport, display_reps, display_cal_in, display_cal, tv_protein, tv_carb,tv_calo,tv_veget,cal_pt,pro_pt,carb_pt,veget_pt, minuteDoneTv, burntIntakeCaloriesTV;
    ProgressBar pb1, pb2, pb3, pb4, pb5, pb6;

    //For containing the data from fragment A
    ArrayList<String> caloriesBurnt;
    ArrayList<String> secondsDoneInSport;
    ArrayList<String> sportType;

    //For the data from fragment B
    SharedPreferences sharedpreferences, sharedpreferences2;
    String height;
    String weight;
    String age;
    double weightInDouble, heightInDouble;
    double calories,  carbs, protein;
    int fruitPortion,age_int;


    //For the data from fragment A
    double totalBurntCalories = 0;
    int totalSecondsDoneInSports = 0;
    ArrayList<Integer> sportRecentDone = new ArrayList<>();
    ArrayList<String> sportsName = new ArrayList<>();



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

        View v = inflater.inflate(R.layout.fragment_progress, container, false);

        init(v);
        fragBData();
        fragAData();
        setData(v);
        setLinearProgressBarData();
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    private void setLinearProgressBarData() {
        //Minutes done in sport
        pb5.post(new Runnable() {
            @Override
            public void run() {
                pb5.setMax(1800);
                pb5.setProgress((int) totalSecondsDoneInSports);
                pb5.setVisibility(View.VISIBLE);

            }
        });
        if(totalSecondsDoneInSports!=0.0)
            minuteDoneTv.setText(String.format("%.2f", (totalSecondsDoneInSports/1800.0)*100) + "%");

        //Burnt calories ratio and intake ratio
        pb6.post(new Runnable() {
            @Override
            public void run() {
                pb6.setMax((int) calories);
                pb6.setProgress((int) totalBurntCalories);
                pb6.setVisibility(View.VISIBLE);
            }
        });
        if(calories!=0.0)
            burntIntakeCaloriesTV.setText(String.format("%.2f", (totalBurntCalories / calories)*100) + "%");
    }

    private void fragAData() {
        //Fragment A in shared preference approach
        sharedpreferences2 = getActivity().getSharedPreferences(SportInstruction.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        if(sharedpreferences2.getStringSet(SportInstruction.CALO_LIST, null) == null){
            //Dont do anything
        }
        else{
            caloriesBurnt = new ArrayList<>();
            secondsDoneInSport = new ArrayList<>();
            sportType = new ArrayList<>();

            caloriesBurnt.addAll(sharedpreferences2.getStringSet(SportInstruction.CALO_LIST, null));
            sportType.addAll(sharedpreferences2.getStringSet(SportInstruction.TYPE_LIST, null));
            secondsDoneInSport.addAll(sharedpreferences2.getStringSet(SportInstruction.REP_LIST, null));
        }
        if(caloriesBurnt!= null)
            processData();
    }

    private void fragBData() {
        sharedpreferences = getActivity().getSharedPreferences(CalorieFragment.mypreference, Context.MODE_PRIVATE);

        height = sharedpreferences.getString(CalorieFragment.User_Height, "");
        weight = sharedpreferences.getString(CalorieFragment.User_Weight, "");
        String caloriesIntake = sharedpreferences.getString(CalorieFragment.User_Calories, "");
        String carbIntake = sharedpreferences.getString(CalorieFragment.User_Carbs, "");
        String proteinIntake = sharedpreferences.getString(CalorieFragment.User_Protein, "");


        if(!height.isEmpty())
            heightInDouble = Double.valueOf(height);

        if(!weight.isEmpty())
            weightInDouble = Double.valueOf(weight);

        if(!sharedpreferences.getString(CalorieFragment.User_Age, "").isEmpty()){
            age = sharedpreferences.getString(CalorieFragment.User_Age, "");
            age_int = Integer.valueOf(age);
        }


        if(!caloriesIntake.isEmpty()){
            calories = Double.valueOf(caloriesIntake);
            carbs = Double.valueOf(carbIntake);
            protein = Double.valueOf(proteinIntake);
            fruitPortion = sharedpreferences.getInt(CalorieFragment.User_fruitPortion, 0);
        }
    }

    private void init(View v) {
        //display_day = v.findViewById(R.id.display_day);
        display_sport = v.findViewById(R.id.display_sport);
        display_reps = v.findViewById(R.id.display_reps);
        display_cal_in = v.findViewById(R.id.display_calories_in);
        display_cal = v.findViewById(R.id.display_calories);
        tv_protein = v.findViewById(R.id.tv_protain);
        tv_carb = v.findViewById(R.id.tv_carbon);
        tv_calo=v.findViewById(R.id.tv_calor);
        tv_veget=v.findViewById(R.id.tv_veget);
        cal_pt=v.findViewById(R.id.cal_progress_text);
        pro_pt=v.findViewById(R.id.pro_progress_text);
        carb_pt=v.findViewById(R.id.carb_progress_text);
        veget_pt=v.findViewById(R.id.veget_progress_text);
        minuteDoneTv = v.findViewById(R.id.minutesDoneProgressTV);
        burntIntakeCaloriesTV = v.findViewById(R.id.burntIntakeCaloriesRatioTV);


        pb1 = v.findViewById(R.id.progressBar1);
        pb2 = v.findViewById(R.id.progressBar2);
        pb3 = v.findViewById(R.id.progressBar3);
        pb4= v.findViewById(R.id.progressBar4);
        pb5 = v.findViewById(R.id.pb5);
        pb5.setScaleY(3f);
        pb6= v.findViewById(R.id.pb6);
        pb6.setScaleY(3f);
    }

    private void setData(View v) {
        setFragAData(v);
        setFragBData(v);
    }

    private void setFragAData(View v) {
        //display_day.setText(day.get(position));
        for(int i=0; i<sportsName.size(); i++){
            if(i == sportsName.size() - 1)
                display_sport.append(sportsName.get(i));

            else
                display_sport.append(sportsName.get(i) + ", ");

            if(i>=2)
                display_sport.setTextSize(10);
        }


        display_reps.setText(String.format("%.2f", totalSecondsDoneInSports / 60.0));



        //display_cal_in.setText(String.format("%.2f", calories));
        display_cal.setText(String.format("%.2f", totalBurntCalories));

    }


    private void processData() {
        //Three things we want to represent
        totalBurntCalories = 0;
        totalSecondsDoneInSports = 0;

        for (String data: secondsDoneInSport){
            for(int i=0; i<data.length(); i++){

                if(data.charAt(i) == ':'){
                    String seconds = "";
                    for(int k=i+1; k<data.length(); k++)
                        seconds+=data.charAt(k);

                    totalSecondsDoneInSports+= Integer.valueOf(seconds);
                }

            }
        }

        for (String data: caloriesBurnt){
            for(int i=0; i<data.length(); i++){

                if(data.charAt(i) == ':'){
                    String burntCaloriesPerSet = "";
                    for(int k=i+1; k<data.length(); k++)
                        burntCaloriesPerSet+=data.charAt(k);

                    totalBurntCalories+= Double.valueOf(burntCaloriesPerSet);
                }

            }
        }

        for (String data: sportType){
            for(int i=0; i<data.length(); i++){

                if(data.charAt(i) == ':'){
                    String sportType = "";
                    for(int k=i+1; k<data.length(); k++)
                        sportType+=data.charAt(k);

                    //In case of duplication
                    if(!sportRecentDone.contains(Integer.valueOf(sportType)))
                        sportRecentDone.add(Integer.valueOf(sportType));
                }

            }
        }

        recentSportDetermination(sportRecentDone);

    }

    private void recentSportDetermination(ArrayList<Integer> sportRecentDone) {
        sportsName = new ArrayList<>();
        for(int data: sportRecentDone){
            switch (data){
                case R.id.sport1:
                    sportsName.add("Home Gym");
                    break;
                case R.id.sport2:
                    sportsName.add("Regular Gym");
                    break;
                case R.id.sport3:
                    sportsName.add("Chest Training");
                    break;
                case R.id.sport4:
                    sportsName.add("Leg Training");
                    break;
                case R.id.sport5:
                    sportsName.add("Yoga");
                    break;
                case R.id.sport6:
                    sportsName.add("Warm up exercise");
                    break;
                default: break;
            }
        }
    }

    private void setFragBData(View v) {

        int targetCal,targetProtein, targetCarbs,targetVeget;
        int it_calo,it_pro,it_carbs,it_fp;

        it_calo=(int)calories;
        it_pro=(int)protein;
        it_carbs=(int)carbs;
        it_fp=(int)fruitPortion;

        if(age_int<4 && age_int!=0){
            targetCal=1000;
        }else if(age_int>3 && age_int<9){
            targetCal=2000;
        }else if(age_int>8 && age_int<14){
            targetCal=2600;
        }else if(age_int>13 && age_int<19){
            targetCal=3200;
        }else if(age_int>18 && age_int<31){
            targetCal=3000;
        }else if(age_int>30 && age_int<51){
            targetCal=3000;
        }else{
            targetCal=2800;
        }


        targetProtein = (int)(0.8 * weightInDouble);
        targetCarbs = (int)(0.55 * targetCal);
        targetVeget= 4;

        cal_pt.setText(String.format("%.2f", (calories/targetCal)*100) + "%");

        if(targetProtein==0){
            pro_pt.setText("0.00%");
        }
        else
            pro_pt.setText(String.format("%.2f", (protein/targetProtein)*100)+ "%");

        carb_pt.setText(String.format("%.2f", (carbs/targetCarbs)*100)+ "%");
        veget_pt.setText( String.format("%.2f", (fruitPortion/4.0) * 100)+ "%");
        //Roughly

        if(sharedpreferences.contains(CalorieFragment.User_Calories)) {
            pb1.setMax(targetCal);
            pb1.setProgress(it_calo);
            tv_calo.setText("Calories(take in)\nTarget: " + String.valueOf(targetCal) + " kcal");
            pb1.post(new Runnable() {
                @Override
                public void run() {
                    pb1.setProgress(it_calo);
                }
            });
        }



        if(sharedpreferences.contains(CalorieFragment.User_Protein)) {
            pb2.setMax(targetProtein);
            pb2.setProgress(it_pro);
            tv_protein.setText("Protein\nTarget: " + String.valueOf(targetProtein));

            pb2.post(new Runnable() {
                @Override
                public void run() {
                    pb2.setProgress(it_pro);
                }
            });
        }



        if(sharedpreferences.contains(CalorieFragment.User_Carbs)) {
            pb3.setMax(targetCarbs);
            pb3.setProgress(it_carbs);
            tv_carb.setText("Carbohydrates\nTarget: " + String.valueOf(targetCarbs));

            pb3.post(new Runnable() {
                @Override
                public void run() {
                    pb3.setProgress(it_carbs);
                }
            });
        }



        if(sharedpreferences.contains(CalorieFragment.User_fruitPortion)) {
            pb4.setMax(targetVeget);
            pb4.setProgress(it_fp);
            tv_veget.setText("Vegetable \n Target: " + targetVeget);

            pb4.post(new Runnable() {
                @Override
                public void run() {
                    pb4.setProgress(it_fp);
                }
            });
        }

    }


}