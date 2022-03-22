package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et1;
    private Button btn1, btn2;
    private Switch switchy;

    private static final String SHARED_PREFERENCE = "sharedPreference";
    private static final String TEXT = "text";
    private static final String SWITCH1 = "switch1";

    //For loading the data when the app is reopened
    private String text;
    private boolean switchStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv1);
        et1 = findViewById(R.id.tv2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        switchy = findViewById(R.id.switchy1);

        //apply text button
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(et1.getText());
            }
        });

        //Save Data button
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });


        loadData();
        updateView();
    }

    public void saveData() {
        //MODE_PRIVATE means no other app could change our preference
        //SHARED_PREFERENCE is the constant we have created
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        //uses the SharedPreference object we have just created for creating an editor object
        SharedPreferences.Editor editor = sp.edit();

        //saving the data into TEXT
        editor.putString(TEXT, tv.getText().toString());
        //saving the state of switch into the SWITCH1
        editor.putBoolean(SWITCH1, switchy.isChecked());

        //Must have for saving to work
        editor.apply();
    }

    //Load the saved data
    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        text = sp.getString(TEXT, "");
        switchStatus = sp.getBoolean(SWITCH1, false);
    }

    //look at the variables that have been updated by the loadData, and manully update the views
    public void updateView() {
        tv.setText(text);
        switchy.setChecked(switchStatus);
    }
}