package com.example.mybus3_4_2020.MapsLine;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.mybus3_4_2020.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsLineActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    String LineName;
    Double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_line);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle data =getIntent().getExtras();
        LineName=data.getString("LineName");
        lat =data.getDouble("lat");
        lng =data.getDouble("lng");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(4);

        // Add a marker in Sydney and move the camera
        LatLng position1 = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(position1).title(LineName));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position1,20.0f));

    }
}
