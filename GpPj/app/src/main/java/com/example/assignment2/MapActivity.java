package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap map;
    MarkerOptions currentPosition, sportVenues;
    ArrayList<LatLng> gymLocations = new ArrayList<>();
    ArrayList<LatLng> yogaRoomLocations = new ArrayList<>();
    View mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment = findViewById(R.id.mapFragment);

        SupportMapFragment fragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        fragment.getMapAsync(this);

        fragment.getMapAsync((OnMapReadyCallback) this);

        init();
    }

    public void init(){
        gymLocations.add(new LatLng(22.29289852303907, 114.19500446703717));
        gymLocations.add(new LatLng(22.291417243751177, 114.20374800863887));
        gymLocations.add(new LatLng(22.288568585610268, 114.18983223116012));
        gymLocations.add(new LatLng(22.285719869414105, 114.21212210482076));

        yogaRoomLocations.add(new LatLng(22.2790869630534, 114.17852209593137));
        yogaRoomLocations.add(new LatLng(22.277895608958897, 114.18079660912997));
        yogaRoomLocations.add(new LatLng(22.27547315767001, 114.17126940290184));
        yogaRoomLocations.add(new LatLng(22.27710136725776, 114.1683082442093));
    }

    //Markers that represent sport venues on the google map
    private void placeMarker() {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.current_location);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 120, 120, false);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(smallMarker);
        //Assume the user is in here
        currentPosition = new MarkerOptions().icon(descriptor).position(new LatLng(22.280683, 114.182870)).title("You");
        map.addMarker(currentPosition);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.gym_location);
        smallMarker = Bitmap.createScaledBitmap(b, 120, 120, false);
        descriptor = BitmapDescriptorFactory.fromBitmap(smallMarker);

        for(LatLng data: gymLocations){
            sportVenues = new MarkerOptions().icon(descriptor).position(data).title("Gym Room");
            map.addMarker(sportVenues);
        }

        for(LatLng data: yogaRoomLocations){
            sportVenues = new MarkerOptions().position(data).title("Yoga Room");
            map.addMarker(sportVenues);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        placeMarker();
        //Setting the position to focus on
        CameraUpdate center = CameraUpdateFactory.newLatLng(currentPosition.getPosition());
        //Setting the zooming ratio
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        map.moveCamera(zoom);
        map.animateCamera(center);
    }
}