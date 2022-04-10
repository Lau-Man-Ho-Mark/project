package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SportInstruction extends AppCompatActivity implements View.OnClickListener {
    PopUpDialogFragment myDialog;
    ImageView instructionImage;
    int sport, repsDoneTotal;
    double  currentBurntCalories = 0;
    Handler handler;
    Button stopBtn, startBtn;
    Button saveBtn;
    //Button StopBtnPopup;
    Runnable currentSportRunnable;
    TextView instructionTV, caloriesBurnt, repsDone, txtclose;
    ArrayList<Integer> pictureList;
    boolean isRunning = false;


    SharedPreferences preferences;
    public static final String SHARED_PREFERENCE = "shared_pref";
    public static final String REP_LIST = "rep_list";
    public static final String CALO_LIST = "calo_list";
    public static boolean isFirstTime = true;

    ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> data2 = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport1);

        //initialise views and set event listener
        init();
        loadData();

        //Determining what type of sport is pressed to get into this activity
        Intent i = getIntent();
        sport = i.getIntExtra("sportType", 0);
        playSportInstruction(sport);

    }

    private void loadData() {
        preferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        if(isFirstTime){
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            isFirstTime = false;
        }
        else{
            data.addAll(preferences.getStringSet(REP_LIST, null));
            data2.addAll(preferences.getStringSet(CALO_LIST, null));
        }

    }

    public void init(){
        instructionTV = findViewById(R.id.instructionTV);
        instructionImage = findViewById(R.id.instructionImage);
        caloriesBurnt = findViewById(R.id.caloriesBurntTV);
        repsDone = findViewById(R.id.repsDone);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }


    private void playSportInstruction(int sport) {

        double caloriesBurntPerRep = 0;
        switch (sport)
        {
            case R.id.sport1:
                caloriesBurntPerRep = 0.8;
                break;
            case R.id.sport2:
                caloriesBurntPerRep = 1.2;
                break;
            case R.id.sport3:
                caloriesBurntPerRep = 1.4;
                break;
            case R.id.sport4:
                caloriesBurntPerRep = 2.4;
                break;
            case R.id.sport5:
                caloriesBurntPerRep = 0.3;
                break;
            case R.id.sport6:
                caloriesBurntPerRep = 0.2;
                break;
        }

        handler = new Handler();
        switch(sport){
            case R.id.sport1:
                instructionTV.setText(R.string.sport1Instruc);
                repsDoneTotal = 0;
                sport1();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;

            case R.id.sport2:
                instructionTV.setText(R.string.sport2Instruc);
                repsDoneTotal = 0;
                sport2();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;

            case R.id.sport3:
                instructionTV.setText(R.string.sport3Instruc);
                repsDoneTotal = 0;
                sport3();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;

            case R.id.sport4:
                instructionTV.setText(R.string.sport4Instruc);
                repsDoneTotal = 0;
                sport4();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;

            case R.id.sport5:
                instructionTV.setText(R.string.sport5Instruc);
                repsDoneTotal = 0;
                sport5();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;

            case R.id.sport6:
                instructionTV.setText(R.string.sport6Instruc);
                repsDoneTotal = 0;
                sport6();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);
                break;


            default: System.out.println("No instruction prepared"); break;
        }
    }

    private void sport6() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s6_one_1n2);
        pictureList.add(R.drawable.s6_one_1n2);
        pictureList.add(R.drawable.s6_two_1n2);
        pictureList.add(R.drawable.s6_two_1n2);
        pictureList.add(R.drawable.s6_three_1n2);
        pictureList.add(R.drawable.s6_three_1n2);
        pictureList.add(R.drawable.s6_four_1n2);
        pictureList.add(R.drawable.s6_four_1n2);
        pictureList.add(R.drawable.s6_five_1n2);
        pictureList.add(R.drawable.s6_five_1n2);
        pictureList.add(R.drawable.s6_six_1n2);
        pictureList.add(R.drawable.s6_six_1n2);
        pictureList.add(R.drawable.s6_seven_1n2);
        pictureList.add(R.drawable.s6_seven_1n2);
    }
    private void sport5() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s5_one_1);
        pictureList.add(R.drawable.s5_one_2);
        pictureList.add(R.drawable.s5_two_1n2);
        pictureList.add(R.drawable.s5_two_1n2);
        pictureList.add(R.drawable.s5_three_1n2);
        pictureList.add(R.drawable.s5_three_1n2);
        pictureList.add(R.drawable.s5_four_1);
        pictureList.add(R.drawable.s5_four_2);
        pictureList.add(R.drawable.s5_fivensix_1);
        pictureList.add(R.drawable.s5_five_2);
        pictureList.add(R.drawable.s5_fivensix_1);
        pictureList.add(R.drawable.s5_six_2);
    }
    private void sport4() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s4_one_1);
        pictureList.add(R.drawable.s4_one_2);
        pictureList.add(R.drawable.s4_two_1);
        pictureList.add(R.drawable.s4_two_2);
        pictureList.add(R.drawable.s4_three_1);
        pictureList.add(R.drawable.s4_three_2);
        pictureList.add(R.drawable.s4_four_1);
        pictureList.add(R.drawable.s4_four_2);
    }
    private void sport3() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s3_one_1);
        pictureList.add(R.drawable.s3_one_2);
        pictureList.add(R.drawable.s3_two_1);
        pictureList.add(R.drawable.s3_two_2);
        pictureList.add(R.drawable.s3_three_1);
        pictureList.add(R.drawable.s3_three_2);
        pictureList.add(R.drawable.s3_four_1);
        pictureList.add(R.drawable.s3_four_2);
        pictureList.add(R.drawable.s3_five_1);
        pictureList.add(R.drawable.s3_five_2);
        pictureList.add(R.drawable.s3_six_1);
        pictureList.add(R.drawable.s3_six_2);
    }
    private void sport2() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s2_one_1);
        pictureList.add(R.drawable.s2_one_2);
        pictureList.add(R.drawable.s2_two_1);
        pictureList.add(R.drawable.s2_two_2);
        pictureList.add(R.drawable.s2_three_1);
        pictureList.add(R.drawable.s2_three_2);
        pictureList.add(R.drawable.s2_four_1);
        pictureList.add(R.drawable.s2_four_2);
        pictureList.add(R.drawable.s2_five_1);
        pictureList.add(R.drawable.s2_five_2);
    }
    private void sport1() {
        pictureList = new ArrayList<>();
        pictureList.add(R.drawable.s1_one_1);
        pictureList.add(R.drawable.s1_one_2);
        pictureList.add(R.drawable.s1_two_1);
        pictureList.add(R.drawable.s1_two_2);
        pictureList.add(R.drawable.s1_three_1);
        pictureList.add(R.drawable.s1_three_2);
        pictureList.add(R.drawable.s1_four_1);
        pictureList.add(R.drawable.s1_four_2);
        pictureList.add(R.drawable.s1_five_1);
        pictureList.add(R.drawable.s1_five_2);
        pictureList.add(R.drawable.s1_six_1);
        pictureList.add(R.drawable.s1_six_2);
    }

    private Runnable createRunnable(ArrayList<Integer> list, double caloriesBurntPerRep) {
        Runnable runnable = new Runnable() {
            int i=0;
            int times = 0;
            @Override
            public void run() {
                caloriesBurnt.setText(String.format("%.2f", currentBurntCalories));
                instructionImage.setBackgroundResource(list.get(i));
                handler.postDelayed(this, 1000);
                //Toast.makeText(SportInstruction.this, "Executed", Toast.LENGTH_SHORT).show();
                if(i%2==0)
                    i+=1;
                else{
                    currentBurntCalories += caloriesBurntPerRep;
                    i-=1;
                    repsDoneTotal++;
                }


                //Since two moves = 1 reps.
                //Change the times >= for more reps. Usually 12 reps for 1 move
                times++;
                if (times >= 4){
                    times = 0;
                    i+=2;
                }
                //Return to initial values
                if(i>=list.size()){
                    i=0;
                    times = 0;
                }
            }
        };
        return runnable;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stopBtn:
                isRunning = false;
                Log.d("Success", "Runnable stopped");
                handler.removeCallbacks(currentSportRunnable);

                saveData();
                Intent i = new Intent(SportInstruction.this, MainActivity.class);
                i.putStringArrayListExtra("repSecList", data);
                i.putStringArrayListExtra("calorList", data2);


                myDialog = new PopUpDialogFragment(currentBurntCalories, repsDoneTotal, i);
                myDialog.show(getSupportFragmentManager(), "MyPopUpFrag");

                break;

            case R.id.startBtn:
                if (isRunning == false){
                    Log.d("Success", "Runnable started");
                    handler.postDelayed(currentSportRunnable, 0);
                    isRunning = true;
                }
                else


                    break;
        }

    }

    private void saveData() {

        preferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        data.add(data.size() + ":" + repsDoneTotal*2);
        data2.add(data2.size() + ":"+ String.format("%.2f", currentBurntCalories));
        Set<String> list1 = new HashSet<String>();
        list1.addAll(data);

        Set<String> list2 = new HashSet<String>();
        list2.addAll(data2);

        editor.putStringSet(REP_LIST, list1);
        editor.putStringSet(CALO_LIST, list2);
        editor.apply();
    }

}