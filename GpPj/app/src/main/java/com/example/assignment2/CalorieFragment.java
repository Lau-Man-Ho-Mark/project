package com.example.assignment2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalorieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalorieFragment extends Fragment implements View.OnClickListener{

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

    double Quantity_Num,Calories_Num,Protein_Num,Carbs_Num;
    static double Total_calories = 0,Total_protein = 0,Total_carbs = 0;
    static TextView Total_Calories,Total_Protein,Total_Carbs;
    static TableRow Total_List,Total_list_List;
    static TextView Total_title;

    //The variables used in the date picker
    Calendar cal = Calendar.getInstance();
    private int year = cal.get(Calendar.YEAR);
    private int month = cal.get(Calendar.MONTH);
    private int day = cal.get(Calendar.DAY_OF_MONTH);
    static int forDay, forMonth, forYear;

    static int fruitPortion = 0;
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
    Spinner Rice_Quantity, Oats_Quantity,Corn_Quantity,Bread_Quantity,Pasta_Quantity,Sorghum_Quantity;


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
    Spinner Lettuce_Quantity,Tomatoes_Quantity,Potatoes_Quantity,Carrots_Quantity,Broccoli_Quantity,Cucumber_Quantity,Pumpkin_Quantity,Okra_Quantity;

    //Meat And Seafood Table//

    Button Meat_And_Seafood;
    TableRow Meat_And_Seafood_List,Meat_And_Seafood_Beef,Meat_And_Seafood_Pork,Meat_And_Seafood_Chicken,Meat_And_Seafood_Lamb,Meat_And_Seafood_Steamed_Fish,Meat_And_Seafood_Salmon,Meat_And_Seafood_Shrimp;

    TextView Beef_Food,Beef_Serving_size,Beef_Calories,Beef_Protein,Beef_Carbs;
    TextView Pork_Food,Pork_Serving_size,Pork_Calories,Pork_Protein,Pork_Carbs;
    TextView Chicken_Food,Chicken_Serving_size,Chicken_Calories,Chicken_Protein,Chicken_Carbs;
    TextView Lamb_Food,Lamb_Serving_size,Lamb_Calories,Lamb_Protein,Lamb_Carbs;
    TextView Steamed_Fish_Food,Steamed_Fish_Serving_size,Steamed_Fish_Calories,Steamed_Fish_Protein,Steamed_Fish_Carbs;
    TextView Salmon_Food,Salmon_Serving_size,Salmon_Calories,Salmon_Protein,Salmon_Carbs;
    TextView Shrimp_Food,Shrimp_Serving_size,Shrimp_Calories,Shrimp_Protein,Shrimp_Carbs;
    ImageButton Beef_Add,Pork_Add,Chicken_Add,Lamb_Add,Steamed_Fish_Add,Salmon_Add,Shrimp_Add;
    Spinner Beef_Quantity,Pork_Quantity,Chicken_Quantity,Lamb_Quantity,Steamed_Fish_Quantity,Salmon_Quantity,Shrimp_Quantity;

    //Fruit Table//

    Button Fruit;
    TableRow Fruit_List,Fruit_Apple,Fruit_Banana,Fruit_Orange,Fruit_Pear,Fruit_Grape,Fruit_Strawberry,Fruit_Cherry;

    TextView Apple_Food,Apple_Serving_size,Apple_Calories,Apple_Protein,Apple_Carbs;
    TextView Banana_Food,Banana_Serving_size,Banana_Calories,Banana_Protein,Banana_Carbs;
    TextView Orange_Food,Orange_Serving_size,Orange_Calories,Orange_Protein,Orange_Carbs;
    TextView Pear_Food,Pear_Serving_size,Pear_Calories,Pear_Protein,Pear_Carbs;
    TextView Grape_Food,Grape_Serving_size,Grape_Calories,Grape_Protein,Grape_Carbs;
    TextView Strawberry_Food,Strawberry_Serving_size,Strawberry_Calories,Strawberry_Protein,Strawberry_Carbs;
    TextView Cherry_Food,Cherry_Serving_size,Cherry_Calories,Cherry_Protein,Cherry_Carbs;
    ImageButton Apple_Add,Banana_Add,Orange_Add,Pear_Add,Grape_Add,Strawberry_Add,Cherry_Add;
    Spinner Apple_Quantity,Banana_Quantity,Orange_Quantity,Pear_Quantity,Grape_Quantity,Strawberry_Quantity,Cherry_Quantity;

    //Diary Table//

    Button Diary;
    TableRow Diary_List,Diary_Egg,Diary_Milk,Diary_Butter,Diary_Cheese,Diary_Yogurt,Diary_Ice_Cream;

    TextView Egg_Food,Egg_Serving_size,Egg_Calories,Egg_Protein,Egg_Carbs;
    TextView Milk_Food,Milk_Serving_size,Milk_Calories,Milk_Protein,Milk_Carbs;
    TextView Butter_Food,Butter_Serving_size,Butter_Calories,Butter_Protein,Butter_Carbs;
    TextView Cheese_Food,Cheese_Serving_size,Cheese_Calories,Cheese_Protein,Cheese_Carbs;
    TextView Yogurt_Food,Yogurt_Serving_size,Yogurt_Calories,Yogurt_Protein,Yogurt_Carbs;
    TextView Ice_Cream_Food,Ice_Cream_Serving_size,Ice_Cream_Calories,Ice_Cream_Protein,Ice_Cream_Carbs;
    ImageButton Egg_Add,Milk_Add,Butter_Add,Cheese_Add,Yogurt_Add,Ice_Cream_Add;
    Spinner Egg_Quantity,Milk_Quantity,Butter_Quantity,Cheese_Quantity,Yogurt_Quantity,Ice_Cream_Quantity;

    Button Save;
    EditText Input_Height,Input_Weight,Input_Age;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String User_Height = "Height_Key";
    public static final String User_Weight = "Weight_Key";
    public static final String User_Age = "Age_Key";
    public static final String User_Calories = "Calories_Key";
    public static final String User_Protein = "Protein_Key";
    public static final String User_Carbs = "Carbs_Key";
    public static final String User_fruitPortion = "User_fruitPortion";

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
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein = v.findViewById(R.id.Total_Protein);
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs = v.findViewById(R.id.Total_Carbs);
        Total_Carbs.setText(String.valueOf((int)Total_carbs));

        Total_List = v.findViewById(R.id.Total_List);
        Total_list_List = v.findViewById(R.id.Total_list_List);
        Total_title = v.findViewById(R.id.Total_title);

        lv1 = v.findViewById(R.id.lv1);
        MyListAdapter adapter=new MyListAdapter(getActivity(),Food,Quantity,Serving_size,Calories,Protein,Carbs);
        lv1.setAdapter(adapter);

        lv1.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
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

        //Meat And Seafood//

        Meat_And_Seafood = v.findViewById(R.id.Meat_And_Seafood);
        Meat_And_Seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Meat_And_Seafood_List = v.findViewById(R.id.Meat_And_Seafood_List);
                Meat_And_Seafood_Beef = v.findViewById(R.id.Meat_And_Seafood_Beef);
                Meat_And_Seafood_Pork = v.findViewById(R.id.Meat_And_Seafood_Pork);
                Meat_And_Seafood_Chicken = v.findViewById(R.id.Meat_And_Seafood_Chicken);
                Meat_And_Seafood_Lamb = v.findViewById(R.id.Meat_And_Seafood_Lamb);
                Meat_And_Seafood_Steamed_Fish = v.findViewById(R.id.Meat_And_Seafood_Steamed_Fish);
                Meat_And_Seafood_Salmon = v.findViewById(R.id.Meat_And_Seafood_Salmon);
                Meat_And_Seafood_Shrimp = v.findViewById(R.id.Meat_And_Seafood_Shrimp);

                if (Meat_And_Seafood_List.getVisibility()==v.VISIBLE) {
                    Meat_And_Seafood_List.setVisibility(v.GONE);
                    Meat_And_Seafood_Beef.setVisibility(v.GONE);
                    Meat_And_Seafood_Pork.setVisibility(v.GONE);
                    Meat_And_Seafood_Chicken.setVisibility(v.GONE);
                    Meat_And_Seafood_Lamb.setVisibility(v.GONE);
                    Meat_And_Seafood_Steamed_Fish.setVisibility(v.GONE);
                    Meat_And_Seafood_Salmon.setVisibility(v.GONE);
                    Meat_And_Seafood_Shrimp.setVisibility(v.GONE);
                } else {
                    Meat_And_Seafood_List.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Beef.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Pork.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Chicken.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Lamb.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Steamed_Fish.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Salmon.setVisibility(v.VISIBLE);
                    Meat_And_Seafood_Shrimp.setVisibility(v.VISIBLE);
                }
            }
        });

        //Fruit//

        Fruit = v.findViewById(R.id.Fruit);
        Fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fruit_List = v.findViewById(R.id.Fruit_List);
                Fruit_Apple = v.findViewById(R.id.Fruit_Apple);
                Fruit_Banana = v.findViewById(R.id.Fruit_Banana);
                Fruit_Orange = v.findViewById(R.id.Fruit_Orange);
                Fruit_Pear = v.findViewById(R.id.Fruit_Pear);
                Fruit_Grape = v.findViewById(R.id.Fruit_Grape);
                Fruit_Strawberry = v.findViewById(R.id.Fruit_Strawberry);
                Fruit_Cherry = v.findViewById(R.id.Fruit_Cherry);

                if (Fruit_List.getVisibility()==v.VISIBLE) {
                    Fruit_List.setVisibility(v.GONE);
                    Fruit_Apple.setVisibility(v.GONE);
                    Fruit_Banana.setVisibility(v.GONE);
                    Fruit_Orange.setVisibility(v.GONE);
                    Fruit_Pear.setVisibility(v.GONE);
                    Fruit_Grape.setVisibility(v.GONE);
                    Fruit_Strawberry.setVisibility(v.GONE);
                    Fruit_Cherry.setVisibility(v.GONE);
                } else {
                    Fruit_List.setVisibility(v.VISIBLE);
                    Fruit_Apple.setVisibility(v.VISIBLE);
                    Fruit_Banana.setVisibility(v.VISIBLE);
                    Fruit_Orange.setVisibility(v.VISIBLE);
                    Fruit_Pear.setVisibility(v.VISIBLE);
                    Fruit_Grape.setVisibility(v.VISIBLE);
                    Fruit_Strawberry.setVisibility(v.VISIBLE);
                    Fruit_Cherry.setVisibility(v.VISIBLE);
                }
            }
        });

        //Diary//

        Diary = v.findViewById(R.id.Diary);
        Diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Diary_List = v.findViewById(R.id.Diary_List);
                Diary_Egg = v.findViewById(R.id.Diary_Egg);
                Diary_Milk = v.findViewById(R.id.Diary_Milk);
                Diary_Butter = v.findViewById(R.id.Diary_Butter);
                Diary_Cheese = v.findViewById(R.id.Diary_Cheese);
                Diary_Yogurt = v.findViewById(R.id.Diary_Yogurt);
                Diary_Ice_Cream = v.findViewById(R.id.Diary_Ice_Cream);

                if (Diary_List.getVisibility()==v.VISIBLE) {
                    Diary_List.setVisibility(v.GONE);
                    Diary_Egg.setVisibility(v.GONE);
                    Diary_Milk.setVisibility(v.GONE);
                    Diary_Butter.setVisibility(v.GONE);
                    Diary_Cheese.setVisibility(v.GONE);
                    Diary_Yogurt.setVisibility(v.GONE);
                    Diary_Ice_Cream.setVisibility(v.GONE);
                } else {
                    Diary_List.setVisibility(v.VISIBLE);
                    Diary_Egg.setVisibility(v.VISIBLE);
                    Diary_Milk.setVisibility(v.VISIBLE);
                    Diary_Butter.setVisibility(v.VISIBLE);
                    Diary_Cheese.setVisibility(v.VISIBLE);
                    Diary_Yogurt.setVisibility(v.VISIBLE);
                    Diary_Ice_Cream.setVisibility(v.VISIBLE);
                }
            }
        });

        //The quantity spinner for all food
        String quan[] = {"1","2","3","4"};
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, quan);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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


        //Setting the adapters
        Rice_Quantity.setAdapter(adapter2);
        Oats_Quantity.setAdapter(adapter2);
        Corn_Quantity.setAdapter(adapter2);
        Bread_Quantity.setAdapter(adapter2);
        Pasta_Quantity.setAdapter(adapter2);
        Sorghum_Quantity.setAdapter(adapter2);


        Rice_Add = v.findViewById(R.id.Rice_Add);
        Rice_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Quantity_Num = Double.parseDouble(Rice_Quantity.getText().toString());
                Quantity_Num = Double.parseDouble((String) Rice_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Oats_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Corn_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Bread_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Pasta_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Sorghum_Quantity.getSelectedItem());
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

       Lettuce_Quantity.setAdapter(adapter2);
       Tomatoes_Quantity.setAdapter(adapter2);
       Potatoes_Quantity.setAdapter(adapter2);
       Carrots_Quantity.setAdapter(adapter2);
       Broccoli_Quantity.setAdapter(adapter2);
       Cucumber_Quantity.setAdapter(adapter2);
       Pumpkin_Quantity.setAdapter(adapter2);
       Okra_Quantity.setAdapter(adapter2);


        Lettuce_Add = v.findViewById(R.id.Lettuce_Add);
        Lettuce_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Lettuce_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Tomatoes_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Potatoes_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Carrots_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Broccoli_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Cucumber_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Pumpkin_Quantity.getSelectedItem());
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
                Quantity_Num = Double.parseDouble((String) Okra_Quantity.getSelectedItem());
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

        //Meat And Seafood Add button//

        Beef_Food = v.findViewById(R.id.Beef_Food);
        Beef_Quantity = v.findViewById(R.id.Beef_Quantity);
        Beef_Serving_size = v.findViewById(R.id.Beef_Serving_size);
        Beef_Calories = v.findViewById(R.id.Beef_Calories);
        Beef_Protein = v.findViewById(R.id.Beef_Protein);
        Beef_Carbs = v.findViewById(R.id.Beef_Carbs);
        Pork_Food = v.findViewById(R.id.Pork_Food);
        Pork_Quantity = v.findViewById(R.id.Pork_Quantity);
        Pork_Serving_size = v.findViewById(R.id.Pork_Serving_size);
        Pork_Calories = v.findViewById(R.id.Pork_Calories);
        Pork_Protein = v.findViewById(R.id.Pork_Protein);
        Pork_Carbs = v.findViewById(R.id.Pork_Carbs);
        Chicken_Food = v.findViewById(R.id.Chicken_Food);
        Chicken_Quantity = v.findViewById(R.id.Chicken_Quantity);
        Chicken_Serving_size = v.findViewById(R.id.Chicken_Serving_size);
        Chicken_Calories = v.findViewById(R.id.Chicken_Calories);
        Chicken_Protein = v.findViewById(R.id.Chicken_Protein);
        Chicken_Carbs = v.findViewById(R.id.Chicken_Carbs);
        Lamb_Food = v.findViewById(R.id.Lamb_Food);
        Lamb_Quantity = v.findViewById(R.id.Lamb_Quantity);
        Lamb_Serving_size = v.findViewById(R.id.Lamb_Serving_size);
        Lamb_Calories = v.findViewById(R.id.Lamb_Calories);
        Lamb_Protein = v.findViewById(R.id.Lamb_Protein);
        Lamb_Carbs = v.findViewById(R.id.Lamb_Carbs);
        Steamed_Fish_Food = v.findViewById(R.id.Steamed_Fish_Food);
        Steamed_Fish_Quantity = v.findViewById(R.id.Steamed_Fish_Quantity);
        Steamed_Fish_Serving_size = v.findViewById(R.id.Steamed_Fish_Serving_size);
        Steamed_Fish_Calories = v.findViewById(R.id.Steamed_Fish_Calories);
        Steamed_Fish_Protein = v.findViewById(R.id.Steamed_Fish_Protein);
        Steamed_Fish_Carbs = v.findViewById(R.id.Steamed_Fish_Carbs);
        Salmon_Food = v.findViewById(R.id.Salmon_Food);
        Salmon_Quantity = v.findViewById(R.id.Salmon_Quantity);
        Salmon_Serving_size = v.findViewById(R.id.Salmon_Serving_size);
        Salmon_Calories = v.findViewById(R.id.Salmon_Calories);
        Salmon_Protein = v.findViewById(R.id.Salmon_Protein);
        Salmon_Carbs = v.findViewById(R.id.Salmon_Carbs);
        Shrimp_Food = v.findViewById(R.id.Shrimp_Food);
        Shrimp_Quantity = v.findViewById(R.id.Shrimp_Quantity);
        Shrimp_Serving_size = v.findViewById(R.id.Shrimp_Serving_size);
        Shrimp_Calories = v.findViewById(R.id.Shrimp_Calories);
        Shrimp_Protein = v.findViewById(R.id.Shrimp_Protein);
        Shrimp_Carbs = v.findViewById(R.id.Shrimp_Carbs);


        Beef_Quantity.setAdapter(adapter2);
        Pork_Quantity.setAdapter(adapter2);
        Chicken_Quantity.setAdapter(adapter2);
        Lamb_Quantity.setAdapter(adapter2);
        Steamed_Fish_Quantity.setAdapter(adapter2);
        Salmon_Quantity.setAdapter(adapter2);
        Shrimp_Quantity.setAdapter(adapter2);

        Beef_Add = v.findViewById(R.id.Beef_Add);
        Beef_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Beef_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Beef_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Beef_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Beef_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Beef_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Beef_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Pork_Add = v.findViewById(R.id.Pork_Add);
        Pork_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Pork_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Pork_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Pork_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Pork_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Pork_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Pork_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Chicken_Add = v.findViewById(R.id.Chicken_Add);
        Chicken_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Chicken_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Chicken_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Chicken_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Chicken_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Chicken_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Chicken_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Lamb_Add = v.findViewById(R.id.Lamb_Add);
        Lamb_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Lamb_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Lamb_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Lamb_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Lamb_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Lamb_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Lamb_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Steamed_Fish_Add = v.findViewById(R.id.Steamed_Fish_Add);
        Steamed_Fish_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Steamed_Fish_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Steamed_Fish_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Steamed_Fish_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Steamed_Fish_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Steamed_Fish_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Steamed_Fish_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Salmon_Add = v.findViewById(R.id.Salmon_Add);
        Salmon_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Salmon_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Salmon_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Salmon_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Salmon_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Salmon_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Salmon_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Shrimp_Add = v.findViewById(R.id.Shrimp_Add);
        Shrimp_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Shrimp_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Shrimp_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Shrimp_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Shrimp_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Shrimp_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Shrimp_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //Fruit button//

        Apple_Food = v.findViewById(R.id.Apple_Food);
        Apple_Quantity = v.findViewById(R.id.Apple_Quantity);
        Apple_Serving_size = v.findViewById(R.id.Apple_Serving_size);
        Apple_Calories = v.findViewById(R.id.Apple_Calories);
        Apple_Protein = v.findViewById(R.id.Apple_Protein);
        Apple_Carbs = v.findViewById(R.id.Apple_Carbs);
        Banana_Food = v.findViewById(R.id.Banana_Food);
        Banana_Quantity = v.findViewById(R.id.Banana_Quantity);
        Banana_Serving_size = v.findViewById(R.id.Banana_Serving_size);
        Banana_Calories = v.findViewById(R.id.Banana_Calories);
        Banana_Protein = v.findViewById(R.id.Banana_Protein);
        Banana_Carbs = v.findViewById(R.id.Banana_Carbs);
        Orange_Food = v.findViewById(R.id.Orange_Food);
        Orange_Quantity = v.findViewById(R.id.Orange_Quantity);
        Orange_Serving_size = v.findViewById(R.id.Orange_Serving_size);
        Orange_Calories = v.findViewById(R.id.Orange_Calories);
        Orange_Protein = v.findViewById(R.id.Orange_Protein);
        Orange_Carbs = v.findViewById(R.id.Orange_Carbs);
        Pear_Food = v.findViewById(R.id.Pear_Food);
        Pear_Quantity = v.findViewById(R.id.Pear_Quantity);
        Pear_Serving_size = v.findViewById(R.id.Pear_Serving_size);
        Pear_Calories = v.findViewById(R.id.Pear_Calories);
        Pear_Protein = v.findViewById(R.id.Pear_Protein);
        Pear_Carbs = v.findViewById(R.id.Pear_Carbs);
        Grape_Food = v.findViewById(R.id.Grape_Food);
        Grape_Quantity = v.findViewById(R.id.Grape_Quantity);
        Grape_Serving_size = v.findViewById(R.id.Grape_Serving_size);
        Grape_Calories = v.findViewById(R.id.Grape_Calories);
        Grape_Protein = v.findViewById(R.id.Grape_Protein);
        Grape_Carbs = v.findViewById(R.id.Grape_Carbs);
        Strawberry_Food = v.findViewById(R.id.Strawberry_Food);
        Strawberry_Quantity = v.findViewById(R.id.Strawberry_Quantity);
        Strawberry_Serving_size = v.findViewById(R.id.Strawberry_Serving_size);
        Strawberry_Calories = v.findViewById(R.id.Strawberry_Calories);
        Strawberry_Protein = v.findViewById(R.id.Strawberry_Protein);
        Strawberry_Carbs = v.findViewById(R.id.Strawberry_Carbs);
        Cherry_Food = v.findViewById(R.id.Cherry_Food);
        Cherry_Quantity = v.findViewById(R.id.Cherry_Quantity);
        Cherry_Serving_size = v.findViewById(R.id.Cherry_Serving_size);
        Cherry_Calories = v.findViewById(R.id.Cherry_Calories);
        Cherry_Protein = v.findViewById(R.id.Cherry_Protein);
        Cherry_Carbs = v.findViewById(R.id.Cherry_Carbs);


        Apple_Quantity.setAdapter(adapter2);
        Banana_Quantity.setAdapter(adapter2);
        Orange_Quantity.setAdapter(adapter2);
        Pear_Quantity.setAdapter(adapter2);
        Grape_Quantity.setAdapter(adapter2);

        Strawberry_Quantity.setAdapter(adapter2);
        Cherry_Quantity.setAdapter(adapter2);
        Apple_Add = v.findViewById(R.id.Apple_Add);
        Apple_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Apple_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Apple_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Apple_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Apple_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Apple_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Apple_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Banana_Add = v.findViewById(R.id.Banana_Add);
        Banana_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Banana_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Banana_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Banana_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Banana_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Banana_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Banana_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Orange_Add = v.findViewById(R.id.Orange_Add);
        Orange_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Orange_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Orange_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Orange_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Orange_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Orange_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Orange_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Pear_Add = v.findViewById(R.id.Pear_Add);
        Pear_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Pear_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Pear_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Pear_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Pear_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Pear_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Pear_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Grape_Add = v.findViewById(R.id.Grape_Add);
        Grape_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Grape_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Grape_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Grape_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Grape_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Grape_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Grape_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Strawberry_Add = v.findViewById(R.id.Strawberry_Add);
        Strawberry_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Strawberry_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Strawberry_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Strawberry_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Strawberry_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Strawberry_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Strawberry_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Cherry_Add = v.findViewById(R.id.Cherry_Add);
        Cherry_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Cherry_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Cherry_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Cherry_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Cherry_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(1, Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Cherry_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Cherry_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //Diary button//



        Egg_Food = v.findViewById(R.id.Egg_Food);
        Egg_Quantity = v.findViewById(R.id.Egg_Quantity);
        Egg_Serving_size = v.findViewById(R.id.Egg_Serving_size);
        Egg_Calories = v.findViewById(R.id.Egg_Calories);
        Egg_Protein = v.findViewById(R.id.Egg_Protein);
        Egg_Carbs = v.findViewById(R.id.Egg_Carbs);
        Milk_Food = v.findViewById(R.id.Milk_Food);
        Milk_Quantity = v.findViewById(R.id.Milk_Quantity);
        Milk_Serving_size = v.findViewById(R.id.Milk_Serving_size);
        Milk_Calories = v.findViewById(R.id.Milk_Calories);
        Milk_Protein = v.findViewById(R.id.Milk_Protein);
        Milk_Carbs = v.findViewById(R.id.Milk_Carbs);
        Butter_Food = v.findViewById(R.id.Butter_Food);
        Butter_Quantity = v.findViewById(R.id.Butter_Quantity);
        Butter_Serving_size = v.findViewById(R.id.Butter_Serving_size);
        Butter_Calories = v.findViewById(R.id.Butter_Calories);
        Butter_Protein = v.findViewById(R.id.Butter_Protein);
        Butter_Carbs = v.findViewById(R.id.Butter_Carbs);
        Cheese_Food = v.findViewById(R.id.Cheese_Food);
        Cheese_Quantity = v.findViewById(R.id.Cheese_Quantity);
        Cheese_Serving_size = v.findViewById(R.id.Cheese_Serving_size);
        Cheese_Calories = v.findViewById(R.id.Cheese_Calories);
        Cheese_Protein = v.findViewById(R.id.Cheese_Protein);
        Cheese_Carbs = v.findViewById(R.id.Cheese_Carbs);
        Yogurt_Food = v.findViewById(R.id.Grape_Food);
        Yogurt_Quantity = v.findViewById(R.id.Yogurt_Quantity);
        Yogurt_Serving_size = v.findViewById(R.id.Yogurt_Serving_size);
        Yogurt_Calories = v.findViewById(R.id.Yogurt_Calories);
        Yogurt_Protein = v.findViewById(R.id.Yogurt_Protein);
        Yogurt_Carbs = v.findViewById(R.id.Yogurt_Carbs);
        Ice_Cream_Food = v.findViewById(R.id.Ice_Cream_Food);
        Ice_Cream_Quantity = v.findViewById(R.id.Ice_Cream_Quantity);
        Ice_Cream_Serving_size = v.findViewById(R.id.Ice_Cream_Serving_size);
        Ice_Cream_Calories = v.findViewById(R.id.Ice_Cream_Calories);
        Ice_Cream_Protein = v.findViewById(R.id.Ice_Cream_Protein);
        Ice_Cream_Carbs = v.findViewById(R.id.Ice_Cream_Carbs);


        Egg_Quantity.setAdapter(adapter2);
        Milk_Quantity.setAdapter(adapter2);
        Butter_Quantity.setAdapter(adapter2);
        Cheese_Quantity.setAdapter(adapter2);
        Yogurt_Quantity.setAdapter(adapter2);
        Ice_Cream_Quantity.setAdapter(adapter2);

        Egg_Add = v.findViewById(R.id.Egg_Add);
        Egg_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Egg_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Egg_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Egg_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Egg_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Egg_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Egg_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Milk_Add = v.findViewById(R.id.Milk_Add);
        Milk_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Milk_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Milk_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Milk_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Milk_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Milk_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Milk_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Butter_Add = v.findViewById(R.id.Butter_Add);
        Butter_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Butter_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Butter_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Butter_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Butter_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Butter_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Butter_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Cheese_Add = v.findViewById(R.id.Cheese_Add);
        Cheese_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Cheese_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Cheese_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Cheese_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Cheese_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Cheese_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Cheese_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Yogurt_Add = v.findViewById(R.id.Yogurt_Add);
        Yogurt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Yogurt_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Yogurt_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Yogurt_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Yogurt_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Yogurt_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Yogurt_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Ice_Cream_Add = v.findViewById(R.id.Ice_Cream_Add);
        Ice_Cream_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity_Num = Double.parseDouble((String) Ice_Cream_Quantity.getSelectedItem());
                Calories_Num = Double.parseDouble(Ice_Cream_Calories.getText().toString());
                Protein_Num = Double.parseDouble(Ice_Cream_Protein.getText().toString());
                Carbs_Num = Double.parseDouble(Ice_Cream_Carbs.getText().toString());

                Calories_Num = Calories_Num * Quantity_Num;
                Protein_Num = Protein_Num * Quantity_Num;
                Carbs_Num = Carbs_Num * Quantity_Num;
                Total_Add(Calories_Num,Protein_Num,Carbs_Num);

                Food.add(Ice_Cream_Food.getText().toString());
                Quantity.add(String.valueOf((int)Quantity_Num));
                Serving_size.add(Ice_Cream_Serving_size.getText().toString());
                Calories.add(String.valueOf((int)Calories_Num));
                Protein.add(String.valueOf((int)Protein_Num));
                Carbs.add(String.valueOf((int)Carbs_Num));

                adapter.notifyDataSetChanged();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Save = v.findViewById(R.id.Save);
        Save.setOnClickListener(this);
        Input_Height = v.findViewById(R.id.Input_Height);
        Input_Weight = v.findViewById(R.id.Input_Weight);
        Input_Age = v.findViewById(R.id.Input_Age);

        //Time picker
        Input_Age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                DatePickerDialog picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
                        int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                        int today = Calendar.getInstance().get(Calendar.DATE);
                        forDay = day;
                        forMonth = month;
                        forYear = year;
                        //You cant be undergrow
                        if(year < thisYear){
                            if(thisMonth > month){
                                //Yet to pass birthday
                                Input_Age.setText(String.valueOf(thisYear-year) );
                            }
                            else if(thisMonth == month){
                                if(today > day)
                                    Input_Age.setText(String.valueOf(thisYear-year) );

                                else
                                    Input_Age.setText(String.valueOf(thisYear-year - 1) );
                            }
                            else
                                Input_Age.setText(String.valueOf(thisYear-year - 1) );

                        }
                        if(year > thisYear)
                            Input_Age.setText("Selected date is invalid");
                        //No matter passed or not. This year born baby is zero
                        if(year == thisYear){
                            if(month > thisMonth)
                                Input_Age.setText("Selected data is invalid");
                            if(month == thisMonth){
                                if(day > today)
                                    Input_Age.setText("Selected data is invalid");
                                else
                                    Input_Age.setText("0");
                            }
                            else
                                Input_Age.setText("0");
                        }

                    }
                }, year, month, day);
                picker.show();
            }
        });

        Log.d("Success", "calorie fragment ready to leave");
        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Save:

                sharedpreferences = this.getActivity().getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(User_Height, Input_Height.getText().toString());
                    editor.putString(User_Weight, Input_Weight.getText().toString());
                    editor.putString(User_Age, Input_Age.getText().toString());

                    editor.putString(User_Calories, String.valueOf((int)Total_calories));
                    editor.putString(User_Protein, String.valueOf((int)Total_protein));
                    editor.putString(User_Carbs, String.valueOf((int)Total_carbs));
                    editor.putInt(User_fruitPortion, fruitPortion);


                    editor.commit();
                    // inform user about SAVE completed
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    public void Total_Add(int isFruit, double Add_Calories,double Add_Protein,double Add_Carbs){
        Total_calories += Add_Calories;
        Total_protein += Add_Protein;
        Total_carbs += Add_Carbs;
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs.setText(String.valueOf((int)Total_carbs));
        fruitPortion+=1;

        if ( Total_calories != 0 ) {
            Total_List.setVisibility(View.VISIBLE);
            Total_list_List.setVisibility(View.VISIBLE);
            Total_title.setVisibility(View.VISIBLE);
        }

    }

    public static void Total_Minus(int isFruit, double Minus_Calories,double Minus_Protein,double Minus_Carbs){
        Total_calories -= Minus_Calories;
        Total_protein -= Minus_Protein;
        Total_carbs -= Minus_Carbs;
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs.setText(String.valueOf((int)Total_carbs));
        fruitPortion-=1;

    }
    public void Total_Add (double Add_Calories,double Add_Protein,double Add_Carbs){
        Total_calories += Add_Calories;
        Total_protein += Add_Protein;
        Total_carbs += Add_Carbs;
        Total_Calories.setText(String.valueOf((int)Total_calories));
        Total_Protein.setText(String.valueOf((int)Total_protein));
        Total_Carbs.setText(String.valueOf((int)Total_carbs));

        if ( Total_calories != 0 ) {
            Total_List.setVisibility(View.VISIBLE);
            Total_list_List.setVisibility(View.VISIBLE);
            Total_title.setVisibility(View.VISIBLE);
        }
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