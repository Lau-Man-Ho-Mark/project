package com.example.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalorieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalorieFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalorieFragment() {
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
    public static CalorieFragment newInstance(String param1, String param2) {
        CalorieFragment fragment = new CalorieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Integer selected_pos = -1; // -1 means nothing is selected

    String[] Food1 = {};
    String[] Quantity1 = {};
    String[] Serving_size1 = {};
    String[] Calories1 = {};
    String[] Protein1 = {};
    String[] Carbs1 = {};

    ArrayList<String> Food = new ArrayList<String>(Arrays.asList(Food1));
    ArrayList<String> Quantity = new ArrayList<String>(Arrays.asList(Quantity1));
    ArrayList<String> Serving_size = new ArrayList<String>(Arrays.asList(Serving_size1));
    ArrayList<String> Calories = new ArrayList<String>(Arrays.asList(Calories1));
    ArrayList<String> Protein = new ArrayList<String>(Arrays.asList(Protein1));
    ArrayList<String> Carbs = new ArrayList<String>(Arrays.asList(Carbs1));
    ListView lv1;

    String[] s1 = {};
    Integer[] c1 = {android.R.drawable.btn_star_big_off,
            android.R.drawable.btn_star_big_on,
            android.R.drawable.btn_radio,
            android.R.drawable.ic_dialog_email};

    ArrayList<String> season = new ArrayList<String>(Arrays.asList(s1));
    ArrayList<Integer> icon = new ArrayList<Integer>(Arrays.asList(c1));

    double Quantity_Num,Calories_Num,Protein_Num,Carbs_Num;
    static double Total_calories = 0,Total_protein = 0,Total_carbs = 0;
    static TextView Total_Calories,Total_Protein,Total_Carbs;

    //Grains Table//

    Button Grains;
    TableRow Grains_List,Grains_Rice,Grains_Oats,Grains_Corn,Grains_Bread,Grains_Pasta,Grains_Sorghum;

    TextView Rice_Food,Rice_Serving_size,Rice_Calories,Rice_Protein,Rice_Carbs;
    TextView Oats_Food,Oats_Serving_size,Oats_Calories,Oats_Protein,Oats_Carbs;
    TextView Corn_Food,Corn_Serving_size,Corn_Calories,Corn_Protein,Corn_Carbs;
    TextView Bread_Food,Bread_Serving_size,Bread_Calories,Bread_Protein,Bread_Carbs;
    TextView Pasta_Food,Pasta_Serving_size,Pasta_Calories,Pasta_Protein,Pasta_Carbs;
    TextView Sorghum_Food,Sorghum_Serving_size,Sorghum_Calories,Sorghum_Protein,Sorghum_Carbs;
    ImageButton Rice_Add,Oats_Add,Corn_Add,Bread_Add,Pasta_Add,Sorghum_Add;
    EditText Rice_Quantity,Oats_Quantity,Corn_Quantity,Bread_Quantity,Pasta_Quantity,Sorghum_Quantity;

    //Vegetable Table//

    Button Vegetable;
    TableRow Vegetable_List,Vegetable_Lettuce,Vegetable_Tomatoes,Vegetable_Potatoes,Vegetable_Carrots,Vegetable_Broccoli,Vegetable_Cucumber,Vegetable_Pumpkin,Vegetable_Okra;

    TextView Lettuce_Food,Lettuce_Serving_size,Lettuce_Calories,Lettuce_Protein,Lettuce_Carbs;
    TextView Tomatoes_Food,Tomatoes_Serving_size,Tomatoes_Calories,Tomatoes_Protein,Tomatoes_Carbs;
    TextView Potatoes_Food,Potatoes_Serving_size,Potatoes_Calories,Potatoes_Protein,Potatoes_Carbs;
    TextView Carrots_Food,Carrots_Serving_size,Carrots_Calories,Carrots_Protein,Carrots_Carbs;
    TextView Broccoli_Food,Broccoli_Serving_size,Broccoli_Calories,Broccoli_Protein,Broccoli_Carbs;
    TextView Cucumber_Food,Cucumber_Serving_size,Cucumber_Calories,Cucumber_Protein,Cucumber_Carbs;
    TextView Pumpkin_Food,Pumpkin_Serving_size,Pumpkin_Calories,Pumpkin_Protein,Pumpkin_Carbs;
    TextView Okra_Food,Okra_Serving_size,Okra_Calories,Okra_Protein,Okra_Carbs;
    ImageButton Lettuce_Add,Tomatoes_Add,Potatoes_Add,Carrots_Add,Broccoli_Add,Cucumber_Add,Pumpkin_Add,Okra_Add;
    EditText Lettuce_Quantity,Tomatoes_Quantity,Potatoes_Quantity,Carrots_Quantity,Broccoli_Quantity,Cucumber_Quantity,Pumpkin_Quantity,Okra_Quantity;

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
        View v = inflater.inflate(R.layout.fragment_calorie, container, false);


        Total_Calories = v.findViewById(R.id.Total_Calories);
        Total_Protein = v.findViewById(R.id.Total_Protein);
        Total_Carbs = v.findViewById(R.id.Total_Carbs);

        lv1 = v.findViewById(R.id.lv1);
        MyListAdapter adapter=new MyListAdapter(getActivity(),Food,Quantity,Serving_size,Calories,Protein,Carbs, season,icon);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //Grains//
        Grains = v.findViewById(R.id.Grains);
        Grains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Grains_List = v.findViewById(R.id.Grains_List);
                Grains_Rice = v.findViewById(R.id.Grains_Rice);
                Grains_Oats = v.findViewById(R.id.Grains_Oats);
                Grains_Corn = v.findViewById(R.id.Grains_Corn);
                Grains_Bread = v.findViewById(R.id.Grains_Bread);
                Grains_Pasta = v.findViewById(R.id.Grains_Pasta);
                Grains_Sorghum = v.findViewById(R.id.Grains_Sorghum);

                if (Grains_List.getVisibility()==v.VISIBLE) {
                    Grains_List.setVisibility(v.GONE);
                    Grains_Rice.setVisibility(v.GONE);
                    Grains_Oats.setVisibility(v.GONE);
                    Grains_Corn.setVisibility(v.GONE);
                    Grains_Bread.setVisibility(v.GONE);
                    Grains_Pasta.setVisibility(v.GONE);
                    Grains_Sorghum.setVisibility(v.GONE);
                } else {
                    Grains_List.setVisibility(v.VISIBLE);
                    Grains_Rice.setVisibility(v.VISIBLE);
                    Grains_Oats.setVisibility(v.VISIBLE);
                    Grains_Corn.setVisibility(v.VISIBLE);
                    Grains_Bread.setVisibility(v.VISIBLE);
                    Grains_Pasta.setVisibility(v.VISIBLE);
                    Grains_Sorghum.setVisibility(v.VISIBLE);
                }
            }
        });

        //Vegetable//
        Vegetable = v.findViewById(R.id.Vegetable);
        Vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Vegetable_List = v.findViewById(R.id.Vegetable_List);
                Vegetable_Lettuce = v.findViewById(R.id.Vegetable_Lettuce);
                Vegetable_Tomatoes = v.findViewById(R.id.Vegetable_Tomatoes);
                Vegetable_Potatoes = v.findViewById(R.id.Vegetable_Potatoes);
                Vegetable_Carrots = v.findViewById(R.id.Vegetable_Carrots);
                Vegetable_Broccoli = v.findViewById(R.id.Vegetable_Broccoli);
                Vegetable_Cucumber = v.findViewById(R.id.Vegetable_Cucumber);
                Vegetable_Pumpkin = v.findViewById(R.id.Vegetable_Pumpkin);
                Vegetable_Okra = v.findViewById(R.id.Vegetable_Okra);

                if (Vegetable_List.getVisibility()==v.VISIBLE) {
                    Vegetable_List.setVisibility(v.GONE);
                    Vegetable_Lettuce.setVisibility(v.GONE);
                    Vegetable_Tomatoes.setVisibility(v.GONE);
                    Vegetable_Potatoes.setVisibility(v.GONE);
                    Vegetable_Carrots.setVisibility(v.GONE);
                    Vegetable_Broccoli.setVisibility(v.GONE);
                    Vegetable_Cucumber.setVisibility(v.GONE);
                    Vegetable_Pumpkin.setVisibility(v.GONE);
                    Vegetable_Okra.setVisibility(v.GONE);
                } else {
                    Vegetable_List.setVisibility(v.VISIBLE);
                    Vegetable_Lettuce.setVisibility(v.VISIBLE);
                    Vegetable_Tomatoes.setVisibility(v.VISIBLE);
                    Vegetable_Potatoes.setVisibility(v.VISIBLE);
                    Vegetable_Carrots.setVisibility(v.VISIBLE);
                    Vegetable_Broccoli.setVisibility(v.VISIBLE);
                    Vegetable_Cucumber.setVisibility(v.VISIBLE);
                    Vegetable_Pumpkin.setVisibility(v.VISIBLE);
                    Vegetable_Okra.setVisibility(v.VISIBLE);
                }
            }
        });

        //Grains Add button//

        Rice_Food = v.findViewById(R.id.Rice_Food);
        Rice_Quantity = v.findViewById(R.id.Rice_Quantity);
        Rice_Serving_size = v.findViewById(R.id.Rice_Serving_size);
        Rice_Calories = v.findViewById(R.id.Rice_Calories);
        Rice_Protein = v.findViewById(R.id.Rice_Protein);
        Rice_Carbs = v.findViewById(R.id.Rice_Carbs);
        Oats_Food = v.findViewById(R.id.Oats_Food);
        Oats_Quantity = v.findViewById(R.id.Oats_Quantity);
        Oats_Serving_size = v.findViewById(R.id.Oats_Serving_size);
        Oats_Calories = v.findViewById(R.id.Oats_Calories);
        Oats_Protein = v.findViewById(R.id.Oats_Protein);
        Oats_Carbs = v.findViewById(R.id.Oats_Carbs);
        Corn_Food = v.findViewById(R.id.Corn_Food);
        Corn_Quantity = v.findViewById(R.id.Corn_Quantity);
        Corn_Serving_size = v.findViewById(R.id.Corn_Serving_size);
        Corn_Calories = v.findViewById(R.id.Corn_Calories);
        Corn_Protein = v.findViewById(R.id.Corn_Protein);
        Corn_Carbs = v.findViewById(R.id.Corn_Carbs);
        Bread_Food = v.findViewById(R.id.Bread_Food);
        Bread_Quantity = v.findViewById(R.id.Bread_Quantity);
        Bread_Serving_size = v.findViewById(R.id.Bread_Serving_size);
        Bread_Calories = v.findViewById(R.id.Bread_Calories);
        Bread_Protein = v.findViewById(R.id.Bread_Protein);
        Bread_Carbs = v.findViewById(R.id.Bread_Carbs);
        Pasta_Food = v.findViewById(R.id.Pasta_Food);
        Pasta_Quantity = v.findViewById(R.id.Pasta_Quantity);
        Pasta_Serving_size = v.findViewById(R.id.Pasta_Serving_size);
        Pasta_Calories = v.findViewById(R.id.Pasta_Calories);
        Pasta_Protein = v.findViewById(R.id.Pasta_Protein);
        Pasta_Carbs = v.findViewById(R.id.Pasta_Carbs);
        Sorghum_Food = v.findViewById(R.id.Sorghum_Food);
        Sorghum_Quantity = v.findViewById(R.id.Sorghum_Quantity);
        Sorghum_Serving_size = v.findViewById(R.id.Sorghum_Serving_size);
        Sorghum_Calories = v.findViewById(R.id.Sorghum_Calories);
        Sorghum_Protein = v.findViewById(R.id.Sorghum_Protein);
        Sorghum_Carbs = v.findViewById(R.id.Sorghum_Carbs);

        Rice_Add = v.findViewById(R.id.Rice_Add);
        Rice_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Rice_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Rice_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Rice_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Rice_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Rice_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Rice_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Oats_Add = v.findViewById(R.id.Oats_Add);
        Oats_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Oats_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Oats_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Oats_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Oats_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Oats_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Oats_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Corn_Add = v.findViewById(R.id.Corn_Add);
        Corn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Corn_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Corn_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Corn_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Corn_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Corn_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Corn_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Bread_Add = v.findViewById(R.id.Bread_Add);
        Bread_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Bread_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Bread_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Bread_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Bread_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Bread_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Bread_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Pasta_Add = v.findViewById(R.id.Pasta_Add);
        Pasta_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Pasta_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Pasta_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Pasta_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Pasta_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Pasta_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Pasta_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Sorghum_Add = v.findViewById(R.id.Sorghum_Add);
        Sorghum_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Sorghum_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Sorghum_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Sorghum_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Sorghum_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Sorghum_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Sorghum_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //Vegetable Add button//
        Lettuce_Food = v.findViewById(R.id.Lettuce_Food);
        Lettuce_Quantity = v.findViewById(R.id.Lettuce_Quantity);
        Lettuce_Serving_size = v.findViewById(R.id.Lettuce_Serving_size);
        Lettuce_Calories = v.findViewById(R.id.Lettuce_Calories);
        Lettuce_Protein = v.findViewById(R.id.Lettuce_Protein);
        Lettuce_Carbs = v.findViewById(R.id.Lettuce_Carbs);
        Tomatoes_Food = v.findViewById(R.id.Tomatoes_Food);
        Tomatoes_Quantity = v.findViewById(R.id.Tomatoes_Quantity);
        Tomatoes_Serving_size = v.findViewById(R.id.Tomatoes_Serving_size);
        Tomatoes_Calories = v.findViewById(R.id.Tomatoes_Calories);
        Tomatoes_Protein = v.findViewById(R.id.Tomatoes_Protein);
        Tomatoes_Carbs = v.findViewById(R.id.Tomatoes_Carbs);
        Potatoes_Food = v.findViewById(R.id.Potatoes_Food);
        Potatoes_Quantity = v.findViewById(R.id.Potatoes_Quantity);
        Potatoes_Serving_size = v.findViewById(R.id.Potatoes_Serving_size);
        Potatoes_Calories = v.findViewById(R.id.Potatoes_Calories);
        Potatoes_Protein = v.findViewById(R.id.Potatoes_Protein);
        Potatoes_Carbs = v.findViewById(R.id.Potatoes_Carbs);
        Carrots_Food = v.findViewById(R.id.Carrots_Food);
        Carrots_Quantity = v.findViewById(R.id.Carrots_Quantity);
        Carrots_Serving_size = v.findViewById(R.id.Carrots_Serving_size);
        Carrots_Calories = v.findViewById(R.id.Carrots_Calories);
        Carrots_Protein = v.findViewById(R.id.Carrots_Protein);
        Carrots_Carbs = v.findViewById(R.id.Carrots_Carbs);
        Broccoli_Food = v.findViewById(R.id.Broccoli_Food);
        Broccoli_Quantity = v.findViewById(R.id.Broccoli_Quantity);
        Broccoli_Serving_size = v.findViewById(R.id.Broccoli_Serving_size);
        Broccoli_Calories = v.findViewById(R.id.Broccoli_Calories);
        Broccoli_Protein = v.findViewById(R.id.Broccoli_Protein);
        Broccoli_Carbs = v.findViewById(R.id.Broccoli_Carbs);
        Cucumber_Food = v.findViewById(R.id.Cucumber_Food);
        Cucumber_Quantity = v.findViewById(R.id.Cucumber_Quantity);
        Cucumber_Serving_size = v.findViewById(R.id.Cucumber_Serving_size);
        Cucumber_Calories = v.findViewById(R.id.Cucumber_Calories);
        Cucumber_Protein = v.findViewById(R.id.Cucumber_Protein);
        Cucumber_Carbs = v.findViewById(R.id.Cucumber_Carbs);
        Pumpkin_Food = v.findViewById(R.id.Pumpkin_Food);
        Pumpkin_Quantity = v.findViewById(R.id.Pumpkin_Quantity);
        Pumpkin_Serving_size = v.findViewById(R.id.Pumpkin_Serving_size);
        Pumpkin_Calories = v.findViewById(R.id.Pumpkin_Calories);
        Pumpkin_Protein = v.findViewById(R.id.Pumpkin_Protein);
        Pumpkin_Carbs = v.findViewById(R.id.Pumpkin_Carbs);
        Okra_Food = v.findViewById(R.id.Okra_Food);
        Okra_Quantity = v.findViewById(R.id.Okra_Quantity);
        Okra_Serving_size = v.findViewById(R.id.Okra_Serving_size);
        Okra_Calories = v.findViewById(R.id.Okra_Calories);
        Okra_Protein = v.findViewById(R.id.Okra_Protein);
        Okra_Carbs = v.findViewById(R.id.Okra_Carbs);

        Lettuce_Add = v.findViewById(R.id.Lettuce_Add);
        Lettuce_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Lettuce_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Lettuce_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Lettuce_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Lettuce_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Lettuce_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Lettuce_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Tomatoes_Add = v.findViewById(R.id.Tomatoes_Add);
        Tomatoes_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Tomatoes_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Tomatoes_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Tomatoes_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Tomatoes_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Tomatoes_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Tomatoes_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Potatoes_Add = v.findViewById(R.id.Potatoes_Add);
        Potatoes_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Potatoes_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Potatoes_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Potatoes_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Potatoes_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Potatoes_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Potatoes_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Carrots_Add = v.findViewById(R.id.Carrots_Add);
        Carrots_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Carrots_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Carrots_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Carrots_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Carrots_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Carrots_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Carrots_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Broccoli_Add = v.findViewById(R.id.Broccoli_Add);
        Broccoli_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Broccoli_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Broccoli_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Broccoli_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Broccoli_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Broccoli_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Broccoli_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Cucumber_Add = v.findViewById(R.id.Cucumber_Add);
        Cucumber_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Cucumber_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Cucumber_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Cucumber_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Cucumber_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Cucumber_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Cucumber_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Pumpkin_Add = v.findViewById(R.id.Pumpkin_Add);
        Pumpkin_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Pumpkin_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Pumpkin_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Pumpkin_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Pumpkin_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Pumpkin_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Pumpkin_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Okra_Add = v.findViewById(R.id.Okra_Add);
        Okra_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble(Okra_Quantity.getText().toString());
                Calories_Num = Double.parseDouble(Okra_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Okra_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Okra_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Okra_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Okra_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //Get the data
        //init(v);
        /*
        bundle = this.getArguments();
        if(bundle != null){
        }*/


        Log.d("Success", "calorie fragment ready to leave");
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    public static void Total_Add (double Add_Calories,double Add_Protein,double Add_Carbs){
        Total_calories += Add_Calories;
        Total_protein += Add_Protein;
        Total_carbs += Add_Carbs;
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs.setText(String.valueOf((int)Total_carbs));
    }

    public static void Total_Minus (double Minus_Calories,double Minus_Protein,double Minus_Carbs){
        Total_calories -= Minus_Calories;
        Total_protein -= Minus_Protein;
        Total_carbs -= Minus_Carbs;
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs.setText(String.valueOf((int)Total_carbs));
    }
}