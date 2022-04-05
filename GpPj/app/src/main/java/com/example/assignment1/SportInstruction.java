package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SportInstruction extends AppCompatActivity implements View.OnClickListener {
    ImageView instructionImage;
    int sport, repsDoneTotal;
    double  currentBurntCalories = 0;
    Handler handler;
    Button stopBtn;
    Runnable currentSportRunnable;
    TextView instructionTV, caloriesBurnt, repsDone;
    ArrayList<Integer> pictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport1);

        //initialise views and set event listener
        init();
        //Determining what type of sport is pressed to get into this activity
        Intent i = getIntent();
        sport = i.getIntExtra("sportType", 0);
        playSportInstruction(sport);



    }
    public void init(){
        instructionTV = findViewById(R.id.instructionTV);
        stopBtn = findViewById(R.id.stopBtn);
        instructionImage = findViewById(R.id.instructionImage);
        caloriesBurnt = findViewById(R.id.caloriesBurntTV);
        repsDone = findViewById(R.id.repsDone);
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
        }

        handler = new Handler();
        switch(sport){
            case R.id.sport1:
                instructionTV.setText(R.string.sport1Instruc);
                repsDoneTotal = 0;
                sport1();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);

                //start button?
                handler.postDelayed(currentSportRunnable, 0);

                //instructionImage.setImageResource(R.drawable.);
                        //pictureList.add sport 1 pictures
                        //create a new runnable and assign it into currentRunnable
                        //handler start the thread
                break;
            case R.id.sport2:
                instructionTV.setText(R.string.sport2Instruc);
                repsDoneTotal = 0;
                sport2();
                currentSportRunnable = createRunnable(pictureList, caloriesBurntPerRep);

                //start button?
                handler.postDelayed(currentSportRunnable, 0);

                //Setting the background resource at the drawable image list
                //instructionImage.setBackgroundResource(R.drawable.sport1_animation_1);
                //AnimationDrawable frame = (AnimationDrawable) instructionImage.getBackground();
                //frame.start();
                break;

            default: System.out.println("No instruction prepared"); break;
        }
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
                handler.removeCallbacks(currentSportRunnable);

                //Change to pop up dialog?
                repsDone.setText("Reps done: "+ repsDoneTotal);
                break;
        }

    }
}