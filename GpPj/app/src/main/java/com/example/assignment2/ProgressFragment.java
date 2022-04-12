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




    Bundle bundle;
    TextView display_day, display_sport, display_reps, display_cal_in, display_cal, tv_protein, tv_carb;
    ProgressBar pb1, pb2, pb3;

    //For containing the data from fragment A
    ArrayList<String> caloriesBurnt = new ArrayList<>();
    ArrayList<String> secondsDoneInSport = new ArrayList<>();
    ArrayList<String> sportType = new ArrayList<>();

    //For the data from fragment B
    SharedPreferences sharedpreferences;
    String height, weight, age;
    double weightInDouble, heightInDouble;
    double calories,  carbs, protein;
    int fruitPortion;

    //For the data from fragment A
    double totalBurntCalories = 0;
    int totalSecondsDoneInSports = 0;
    ArrayList<Integer> sportRecentDone = new ArrayList<>();
    ArrayList<String> sportsName = new ArrayList<>();
    public static boolean isFirstTime = true;


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
        loadData();

        bundle = getArguments();
        if(bundle != null)
            wrapData();


        setData(v);

        /*Not used
        SimpleDateFormat systime = new SimpleDateFormat("dd.MM.yyyy");
        String currentDateandTime = systime.format(new Date());*/


        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    private void loadData() {
        sharedpreferences = getActivity().getSharedPreferences(CalorieFragment.mypreference, Context.MODE_PRIVATE);

        if(isFirstTime){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            isFirstTime = false;
        }
        else{

            height = sharedpreferences.getString(CalorieFragment.User_Height, "");
            weight = sharedpreferences.getString(CalorieFragment.User_Weight, "");
            String caloriesIntake = sharedpreferences.getString(CalorieFragment.User_Calories, "");
            String carbIntake = sharedpreferences.getString(CalorieFragment.User_Carbs, "");
            String proteinIntake = sharedpreferences.getString(CalorieFragment.User_Protein, "");

            //Fragment B
            sharedpreferences = getActivity().getSharedPreferences(CalorieFragment.mypreference, Context.MODE_PRIVATE);

            if(height!="")
                heightInDouble = Double.valueOf(height);

            if(weight!="")
                weightInDouble = Double.valueOf(weight);

            if(sharedpreferences.getString(CalorieFragment.User_Age, "") != "")
                age = sharedpreferences.getString(CalorieFragment.User_Age, "");

            if(caloriesIntake!=""){
                calories = Double.valueOf(caloriesIntake);
                carbs = Double.valueOf(carbIntake);
                protein = Double.valueOf(proteinIntake);
            }

            fruitPortion = sharedpreferences.getInt(CalorieFragment.User_fruitPortion, 0);

        }
    }

    private void init(View v) {
         display_day = v.findViewById(R.id.display_day);
         display_sport = v.findViewById(R.id.display_sport);
         display_reps = v.findViewById(R.id.display_reps);
         display_cal_in = v.findViewById(R.id.display_calories_in);
         display_cal = v.findViewById(R.id.display_calories);
         tv_protein = v.findViewById(R.id.tv_protain);
         tv_carb = v.findViewById(R.id.tv_carbon);

            //Tv Calories is missing, update yourself
         pb1 = v.findViewById(R.id.progressBar1);
         pb2 = v.findViewById(R.id.progressBar2);
         pb3 = v.findViewById(R.id.progressBar3);



    }

    private void setData(View v) {

        //display_day.setText(day.get(position));
        for(int i=0; i<sportsName.size(); i++){
            if(i == sportsName.size() - 1){
                display_sport.append(sportsName.get(i));
            }
            else
                display_sport.append(sportsName.get(i) + ", ");
        }


        display_reps.setText(String.format("%.2f", totalSecondsDoneInSports / 60.0));
        display_cal_in.setText(String.format("%.2f", calories));
        display_cal.setText(String.format("%.2f", totalBurntCalories));

        int targetProtein, targetCarbs;
        targetProtein = (int)(0.8*weightInDouble);
        targetCarbs = (int)(0.55*calories);
        //Roughly
        pb2.setMax(targetProtein);
        pb2.setProgress((int) protein);
        tv_protein.setText(String.valueOf(targetProtein));


        pb3.setMax(targetCarbs);
        pb3.setProgress((int)carbs);
        tv_carb.setText(String.valueOf(targetCarbs));

    }

    private void wrapData() {
        //Fragment A
        Bundle fragA = bundle.getBundle("fragA");
        caloriesBurnt = fragA.getStringArrayList("burntCaloriesList");
        sportType = fragA.getStringArrayList("sportstypeList");
        secondsDoneInSport = fragA.getStringArrayList("repList");
        //Since the data passed from fragment A is not directly usable
        if(caloriesBurnt!= null)
            processData();


    }

    private void processData() {
        //Three things we want to represent
        totalBurntCalories = 0;
        totalSecondsDoneInSports = 0;

        //sportsDoneRecently;


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

        System.out.println(totalSecondsDoneInSports);
        System.out.println(totalBurntCalories);
        System.out.println(sportRecentDone.size());
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


}