package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageView iv1, iv2, iv3, iv4, iv5, iv6;

    /* Update when needed
    public interface passDataListener{
        public void passData(String name, String height,String weight, int gender, boolean isChecked);
    }

    passDataListener listener;
    Switch switchy;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (passDataListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }
*/



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);

        //Return the view that we have defined. Instead of the default one below!!
        return v;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void init(View v){
        iv1 = v.findViewById(R.id.sport1);
        iv2 = v.findViewById(R.id.sport2);
        iv3 = v.findViewById(R.id.sport3);
        iv4 = v.findViewById(R.id.sport4);
        iv5 = v.findViewById(R.id.sport5);
        iv6 = v.findViewById(R.id.sport6);

        //Register the event listener
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(getActivity(), SportInstruction.class);
        switch(view.getId()){
            case R.id.sport1:
                i.putExtra("sportType", R.id.sport1);
                startActivity(i);
                break;

            case R.id.sport2:
                i.putExtra("sportType", R.id.sport2);
                startActivity(i);
                break;

            case R.id.sport3:
                i.putExtra("sportType", R.id.sport3);
                startActivity(i);
                break;

            case R.id.sport4:
                i.putExtra("sportType", R.id.sport4);
                startActivity(i);
                break;

            case R.id.sport5:
                i.putExtra("sportType", R.id.sport5);
                startActivity(i);
                break;

            case R.id.sport6:
                i.putExtra("sportType", R.id.sport6);
                startActivity(i);
                break;
        }

    }
}