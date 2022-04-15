package com.example.assignment2;

import static com.example.assignment2.CalorieFragment.Total_Minus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> Food;
    private final ArrayList<String> Quantity;
    private final ArrayList<String> Serving_size;
    private final ArrayList<String> Calories;
    private final ArrayList<String> Protein;
    private final ArrayList<String> Carbs;
    private ImageButton List_Delete;

    public MyListAdapter(Context context,
                         ArrayList<String> Food,
                         ArrayList<String> Quantity,
                         ArrayList<String> Serving_size,
                         ArrayList<String> Calories,
                         ArrayList<String> Protein,
                         ArrayList<String> Carbs) {
        super(context, R.layout.activity_listview,Food);
        // inherit the information from super class

        // define the attributes of your class
        this.context=context;
        this.Food=Food;
        this.Quantity=Quantity;
        this.Serving_size=Serving_size;
        this.Calories=Calories;
        this.Protein=Protein;
        this.Carbs=Carbs;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.activity_listview, null, true);

        TextView List_Food = rowView.findViewById(R.id.List_Food);
        TextView List_Quantity = rowView.findViewById(R.id.List_Quantity);
        TextView List_Serving_size = rowView.findViewById(R.id.List_Serving_size);
        TextView List_Calories = rowView.findViewById(R.id.List_Calories);
        TextView List_Protein = rowView.findViewById(R.id.List_Protein);
        TextView List_Carbs = rowView.findViewById(R.id.List_Carbs);

        List_Food.setText(Food.get(position));
        List_Quantity.setText(Quantity.get(position));
        List_Serving_size.setText(Serving_size.get(position));
        List_Calories.setText(Calories.get(position));
        List_Protein.setText(Protein.get(position));
        List_Carbs.setText(Carbs.get(position));

        List_Delete = rowView.findViewById(R.id.List_Delete);
        List_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1,n2,n3;

                n1 = Double.parseDouble(Calories.get(position));
                n2 = Double.parseDouble(Protein.get(position));
                n3 = Double.parseDouble(Carbs.get(position));
                switch(Food.get(position)){
                    case "Apple":
                    case "Orange":
                    case "Banana":
                    case "Pear":
                    case "Grape":
                    case "Strawberry":
                    case "Cherry":
                        CalorieFragment.Total_Minus(1, n1,n2,n3);
                        break;

                    default:
                        CalorieFragment.Total_Minus(n1,n2,n3);
                        break;
                }
                Food.remove(position);
                Quantity.remove(position);
                Serving_size.remove(position);
                Calories.remove(position);
                Protein.remove(position);
                Carbs.remove(position);


                MyListAdapter.this.notifyDataSetChanged();
            }
        });



        return rowView;
    };
}
