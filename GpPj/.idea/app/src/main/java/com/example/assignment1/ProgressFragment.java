package com.example.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
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

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String SHARED_PREFERENCE = "shared_pref";
    public static final String REP_LIST = "rep_list";
    public static final String CALO_LIST = "calo_list";
    public static final String CALIN_LIST="calin_list";
    public static final String SPORT_LIST="sport_list";


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
    ListView progress_lv;
    Button btn_add;

    String[] s1 = {"rep","calo","what","nothing"};
    Integer[] c1 = {android.R.drawable.btn_star_big_off,
            android.R.drawable.btn_star_big_on,
            android.R.drawable.btn_radio,
            android.R.drawable.ic_dialog_email};

    ArrayList<String> season = new ArrayList<String>(Arrays.asList(s1));  //暫時用於store reps
    ArrayList<Integer> icon = new ArrayList<Integer>(Arrays.asList(c1));



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

        progress_lv = v.findViewById(R.id.progresslv);
        MyListAdapter2 adapter = new MyListAdapter2(getContext(), day,sport,reps,cal_in,cal);
        progress_lv.setAdapter(adapter);


        progress_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        SimpleDateFormat systime = new SimpleDateFormat("dd.MM.yyyy");
        String currentDateandTime = systime.format(new Date());

        sharedpreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                day.add(String.valueOf(currentDateandTime));
                sport.add("das");
                reps.add(sharedpreferences.getString(REP_LIST,""));
                cal.add(sharedpreferences.getString(CALO_LIST,""));
                cal_in.add("asd");
                adapter.notifyDataSetChanged();

                }
        });
        //Get the data
        //init(v);
        /*
        bundle = this.getArguments();
        if(bundle != null){
            getData(bundle);
            setData(bundle);
            setGender(gender);
            zodiacTv.setText(zodiacDetermination(forDay, forMonth));
            zodiacImage(zodiacDetermination(forDay, forMonth));
        }*/

        Log.d("Success", "progress fragment ready to leave");
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }


}