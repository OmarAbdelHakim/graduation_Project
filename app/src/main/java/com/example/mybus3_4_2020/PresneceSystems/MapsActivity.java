package com.example.mybus3_4_2020.PresneceSystems;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.mybus3_4_2020.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String email ;
    DatabaseReference locations ;
    Double lat,lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Ref to firbase first
        locations = FirebaseDatabase.getInstance().getReference("Locations");




        //get Intent
        if(getIntent() != null)
        {
            email = getIntent().getStringExtra("email");
            lat = getIntent().getDoubleExtra("lat" , 0 );
            lng = getIntent().getDoubleExtra("lng" , 0 );
        }
        if(!TextUtils.isEmpty(email))
            LoadLocationForthisUser(email);



    }

    private void LoadLocationForthisUser(String email) {

        Query user_locations = locations.orderByChild("email").equalTo(email);
        user_locations.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())


                {
                    Tracking tracking = postSnapshot.getValue(Tracking.class);

                    // add marker for friend location

                    LatLng friendlocations = new LatLng(Double.parseDouble(tracking.getLat()) ,
                            Double.parseDouble(tracking.getLng()));


                    //create locations form user coordinate
                    Location currentUser = new Location("");
                    currentUser.setLongitude(lng);
                    currentUser.setLatitude(lat);

                    //create locations form friend coordinate
                    Location Friend = new Location("");
                    Friend.setLatitude(Double.parseDouble(tracking.getLat()));
                    Friend.setLongitude(Double.parseDouble(tracking.getLng()));

// clear old marker


                    mMap.clear();




                    //Create Functions Calculate the distance
                    //distance(currentUser ,Friend);

                    // Add friend marker on the map

                    mMap.addMarker(new MarkerOptions()

                            . position(friendlocations)

                            .title(tracking.getEmail())
                            .snippet("Distance    " + new DecimalFormat("#.#").format((currentUser . distanceTo(Friend)) / 1000)+ " k.m")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    // dah mohm gdn
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat , lng) , 12.0f));

                }

                // create marker for current user

                LatLng current = new LatLng(lat , lng);
                mMap.addMarker(new MarkerOptions().position(current).title(FirebaseAuth.getInstance().getCurrentUser().getEmail()));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private double distance(Location currentUser, Location friend) {

        double theta = currentUser.getLongitude() - friend.getLongitude();
        double dist = Math.sin(deg2rad(currentUser.getLatitude()))
                * Math.sin(deg2rad(friend.getLatitude()))
                * Math.cos(deg2rad(currentUser.getLatitude()))
                * Math.cos(deg2rad(friend.getLatitude()))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 ;
        return (dist);


    }

    private double rad2deg(double rad) {
        return (rad * 180  / Math.PI);

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

    }
}

