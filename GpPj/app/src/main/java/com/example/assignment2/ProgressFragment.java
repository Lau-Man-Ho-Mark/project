package com.example.assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressFragment extends Fragment implements View.OnClickListener{

    ImageButton day1,day2,day3,day4,day6,day7,day8,day9,day11,day12,day13,day14,day16,day17,day18,day19,day21,day22,day23,day24,day26,day27,day28,day29,restday;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();
    Bundle bundle;
    String height, weight, age, carb, calories, protein;
    int fruitPortion = 0;
    SharedPreferences fragBData;

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


        //Get the data
        //init(v);

        bundle = this.getArguments();
        wrapData(bundle);


        Log.d("Success", "progress fragment ready to leave");
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    private void wrapData(Bundle bundle) {

        //Get Fragment A data here
        if(bundle != null){
            Bundle FragABundle = bundle.getBundle("fragA");
            arr1 = FragABundle.getStringArrayList("repList");
            arr2 = FragABundle.getStringArrayList("burntCaloriesList");

            if(arr1 != null && arr2 != null){
                for (String data: arr1)
                    Log.d("Success", data);

                for (String data: arr2)
                    Log.d("Success", data);

            }
        }
        //Get Fragment B data here
        fragBData = getActivity().getSharedPreferences(CalorieFragment.mypreference, Context.MODE_PRIVATE);
        height = fragBData.getString(CalorieFragment.User_Height, "");
        weight = fragBData.getString(CalorieFragment.User_Weight, "");
        age = fragBData.getString(CalorieFragment.User_Age, "");
        calories = fragBData.getString(CalorieFragment.User_Calories, "");
        protein = fragBData.getString(CalorieFragment.User_Protein, "");
        fruitPortion = fragBData.getInt(CalorieFragment.User_fruitPortion, 0);

        Log.d("Success", calories);
        Log.d("Success", fruitPortion + " is eaten");
    }

    public void init(View v){
        day1=v.findViewById(R.id.day1);
        day2=v.findViewById(R.id.day2);
        day3=v.findViewById(R.id.day3);
        day4=v.findViewById(R.id.day4);
        day6=v.findViewById(R.id.day6);
        day7=v.findViewById(R.id.day7);
        day8=v.findViewById(R.id.day8);
        day9=v.findViewById(R.id.day9);
        day11=v.findViewById(R.id.day11);
        day12=v.findViewById(R.id.day12);
        day13=v.findViewById(R.id.day13);
        day14=v.findViewById(R.id.day14);
        day16=v.findViewById(R.id.day16);
        day17=v.findViewById(R.id.day17);
        day18=v.findViewById(R.id.day18);
        day19=v.findViewById(R.id.day19);
        day21=v.findViewById(R.id.day21);
        day22=v.findViewById(R.id.day22);
        day23=v.findViewById(R.id.day23);
        day24=v.findViewById(R.id.day24);
        day26=v.findViewById(R.id.day26);
        day27=v.findViewById(R.id.day27);
        day28=v.findViewById(R.id.day28);
        day29=v.findViewById(R.id.day29);
        restday=v.findViewById(R.id.day5);
        restday=v.findViewById(R.id.day10);
        restday=v.findViewById(R.id.day15);
        restday=v.findViewById(R.id.day20);
        restday=v.findViewById(R.id.day25);
        restday=v.findViewById(R.id.day30);
    }

    @Override
    public void onClick(View view) {

    }
}