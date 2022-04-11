package com.example.assignment2;

import static com.example.assignment2.CalorieFragment.Total_Minus;

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
    private final ArrayList<String> pro_in;
    private final ArrayList<String> carb_in;
    private final ArrayList<String> cal;
    private ImageButton delete;

    public MyListAdapter2(Context context,
                          ArrayList<String> day,
                          ArrayList<String> sport,
                          ArrayList<String> reps,
                          ArrayList<String> cal_in,
                          ArrayList<String> pro_in,
                          ArrayList<String> carb_in,
                          ArrayList<String> cal) {
        super(context, R.layout.activity_progress_listview, day);
        // inherit the information from super class

        // define the attributes of your class
        this.context = context;
        this.day = day;
        this.sport = sport;
        this.reps = reps;
        this.cal_in = cal_in;
        this.pro_in = pro_in;
        this.carb_in = carb_in;
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
        TextView cal_progress_text=rowView.findViewById(R.id.cal_progress_text);
        TextView pro_progress_text=rowView.findViewById(R.id.pro_progress_text);
        TextView carb_progress_text=rowView.findViewById(R.id.carb_progress_text);
        ProgressBar cal_progress_bar=rowView.findViewById(R.id.cal_progress_bar);
        ProgressBar pro_progress_bar=rowView.findViewById(R.id.pro_progress_bar);
        ProgressBar carb_progress_bar=rowView.findViewById(R.id.carb_progress_bar);

        display_day.setText(day.get(position));
        display_sport.setText(sport.get(position));
        display_reps.setText(reps.get(position));
        display_cal_in.setText(cal_in.get(position));
        display_cal.setText(cal.get(position));
        cal_progress_text.setText(cal_in.get(position));
        pro_progress_text.setText(pro_in.get(position));
        carb_progress_text.setText(carb_in.get(position));

        delete = rowView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.remove(position);
                MyListAdapter2.this.notifyDataSetChanged();
            }
        });

        return rowView;
    }
}

