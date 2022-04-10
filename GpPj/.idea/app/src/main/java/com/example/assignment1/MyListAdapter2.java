package com.example.assignment1;

import static com.example.assignment1.CalorieFragment.Total_Minus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;

public class  MyListAdapter2 extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> day;
    private final ArrayList<String> sport;
    private final ArrayList<String> reps;
    private final ArrayList<String> cal_in;
    private final ArrayList<String> cal;

    public MyListAdapter2(Context context,
                          ArrayList<String> day,
                          ArrayList<String> sport,
                          ArrayList<String> reps,
                          ArrayList<String> cal_in,
                          ArrayList<String> cal) {
        super(context, R.layout.activity_progress_listview, day);
        // inherit the information from super class

        // define the attributes of your class
        this.context = context;
        this.day = day;
        this.sport = sport;
        this.reps = reps;
        this.cal_in = cal_in;
        this.cal = cal;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.activity_progress_listview, null, true);

        TextView display_day = rowView.findViewById(R.id.display_day);
        TextView display_sport = rowView.findViewById(R.id.display_sport);
        TextView display_reps = rowView.findViewById(R.id.display_reps);
        TextView display_cal_in = rowView.findViewById(R.id.display_calories_in);
        TextView display_cal = rowView.findViewById(R.id.display_calories);

        display_day.setText(day.get(position));
        display_sport.setText(sport.get(position));
        display_reps.setText(reps.get(position));
        display_cal_in.setText(cal_in.get(position));
        display_cal.setText(cal.get(position));


        return rowView;
    }
}

