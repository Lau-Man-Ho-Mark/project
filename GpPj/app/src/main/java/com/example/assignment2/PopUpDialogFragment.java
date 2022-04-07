package com.example.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PopUpDialogFragment extends DialogFragment{
    TextView xTv, burntCaloriesNumber, repsDoneNumber;
    double burntCalories;
    int repsDone;

    //Constructor
    PopUpDialogFragment(double burntCalories, int repsDone){
        this.burntCalories = burntCalories;
        this.repsDone = repsDone;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View v = inflater.inflate(R.layout.popup, container, false);

        repsDoneNumber = v.findViewById(R.id.repsDoneNumber);
        burntCaloriesNumber = v.findViewById(R.id.burntCaloriesNumber);

        //Setting the text
        repsDoneNumber.setText(String.valueOf(this.repsDone));
        burntCaloriesNumber.setText(String.format("%.2f", burntCalories));

         xTv = v.findViewById(R.id.txtclose);
         xTv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getActivity().onBackPressed();
             }
         });

         return v;
    }


}
