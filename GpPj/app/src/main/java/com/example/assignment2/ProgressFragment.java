package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressFragment extends Fragment implements View.OnClickListener{

    Integer selected_pos = -1;
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();
    Bundle bundle;
    String height, weight, age, carb, calories, protein;
    String repslist, calolist;
    int fruitPortion = 0;
    SharedPreferences fragBData;

    ListView progress_lv;
    Button btn_add;

    String[] day1 = {};
    String[] sport1 = {};
    String[] reps1 = {};
    String[] cal_in1 = {};
    String[] cal1 = {};



    ArrayList<String> day = new ArrayList<String>(Arrays.asList(day1));
    ArrayList<String> sport = new ArrayList<String>(Arrays.asList(sport1));
    ArrayList<String> reps= new ArrayList<String>(Arrays.asList(reps1));
    ArrayList<String> cal_in = new ArrayList<String>(Arrays.asList(cal_in1));
    ArrayList<String> cal = new ArrayList<String>(Arrays.asList(cal1));

    public static final String SPORT_TYPE = "Sport_Type";
    SharedPreferences fragAdata;

    SharedPreferences homePreferences;
    public static final String mypreference = "mypref";
    public static final String SPORTTYPE = "sport_type";

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



        progress_lv = v.findViewById(R.id.progresslv);
        MyListAdapter2 adapter = new MyListAdapter2(getContext(),  day,sport,reps,cal_in,cal);
        progress_lv.setAdapter(adapter);


        progress_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(getActivity().getApplicationContext(),
                        String.valueOf(position)+": "+selected,
                        Toast.LENGTH_LONG).show();
                Log.d("Jack check", String.valueOf(position)+": "+selected);

                selected_pos = position; // remember which item is selected
                //et1.setText(selected); // show selected season to edittext
            }
        });

        SimpleDateFormat systime = new SimpleDateFormat("dd.MM.yyyy");
        String currentDateandTime = systime.format(new Date());
        btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected_pos == -1) { // means nothing selected to update

                    try {
                        homePreferences = getActivity().getSharedPreferences(HomeFragment.mypreference, Context.MODE_PRIVATE);

                        day.add(String.valueOf(currentDateandTime));
                        sport.add(homePreferences.getString(HomeFragment.SPORTTYPE, ""));
                        reps.add(arr1.get(0));
                        cal.add(arr2.get(0));
                        cal_in.add(calories);
                        adapter.notifyDataSetChanged();
                        //reps.add()
                        // then new item is added
                        //season.add(warp.getString(REP_LIST,""));
                        //arr1.add(sharedpreferences.getString(REP_LIST,""));
                        Log.d("ChecK", "success");
                        //icon.add(android.R.drawable.ic_dialog_map);
                    }catch (Exception exception){
                        Log.d("Check", " No Input ");
                    };
                } else { //  something is selected to udpate
                    reps.set(selected_pos, arr1.get(0).toString());
                    cal.set(selected_pos, arr2.get(0).toString());
                }
                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


        Log.d("Success", "progress fragment ready to leave");
        return v;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);+

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
//        fragAdata = getActivity().getSharedPreferences(SportInstruction.SHARED_PREFERENCE, Context.MODE_PRIVATE);
//        repslist = fragAdata.getString(SportInstruction.REP_LIST,"");
//        calolist = fragAdata.getString(SportInstruction.CALO_LIST,"");
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

    }

    @Override
    public void onClick(View view) {

    }
}