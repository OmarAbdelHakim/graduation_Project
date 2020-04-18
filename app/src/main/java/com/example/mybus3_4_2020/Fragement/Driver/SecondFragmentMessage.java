package com.example.mybus3_4_2020.Fragement.Driver;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.mybus3_4_2020.PresneceSystems.ListOnlineViewHolder;
import com.example.mybus3_4_2020.PresneceSystems.MapsActivity;
import com.example.mybus3_4_2020.PresneceSystems.Tracking;
import com.example.mybus3_4_2020.PresneceSystems.User;
import com.example.mybus3_4_2020.R;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mybus3_4_2020.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.location.LocationListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragmentMessage extends   Fragment implements
        GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , LocationListener {


    DatabaseReference onlineref , CureentUserref , CounterRef , locations;
    FirebaseRecyclerAdapter<User, ListOnlineViewHolder> adapter ;
    // view
    RecyclerView listOnline;
    RecyclerView.LayoutManager layoutManager;

    //Location
    private static final int MY_PERMISSION_REQUEST_CODE = 7171;
    private static final int PLAY_SERVICES_RES_REQUEST = 7172;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private static final int UPDATE_INTERVAL = 5000;
    private static final int FASTEST_INTERVAL = 3000;
    private static final int DISTANCE = 10;


    public SecondFragmentMessage() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second_message , container , false);


      //  ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        listOnline = v.findViewById(R.id.listOnline);
        listOnline.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        listOnline.setLayoutManager(layoutManager);
        //set toolbar and layout / joinMenue
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolBar);
        toolbar.setTitle("Location");
        ((AppCompatActivity) getActivity()).setSupportActionBar( toolbar);
        locations = FirebaseDatabase.getInstance().getReference("Locations");
        onlineref = FirebaseDatabase.getInstance().getReference().child(".info/connected");
        CounterRef = FirebaseDatabase.getInstance().getReference("last Online");// create a new child named lastOnline
        CureentUserref = FirebaseDatabase.getInstance().getReference("last Online").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        if(ActivityCompat.checkSelfPermission(getActivity() , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity() , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity()  , new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION ,
                    Manifest.permission.ACCESS_FINE_LOCATION
            } , MY_PERMISSION_REQUEST_CODE );
        }

        else
        {
            if(CheckPlayServices())
            {
                BuildGoogleApiClient();
                createLocationRequest();
                displayLocation();
            }
        }


        // create a new child in last online with uid
        SetupSystems();
        //after setup systems we just load all user from counter ref and display it in resyclerView
        // this Online LIst
        updateList();

        return v;

    }

    private void displayLocation() {
        if(ActivityCompat.checkSelfPermission(getActivity() , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity() , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        if(mLastLocation !=null)
        {
            // update to Firebase
            locations.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(new Tracking(FirebaseAuth.getInstance().getCurrentUser().getEmail()  ,
                            FirebaseAuth.getInstance().getUid() ,  String.valueOf(mLastLocation.getLatitude()),
                            String.valueOf(mLastLocation.getLongitude())));


        }
        else
        {
            //Toast.makeText(this, "Couldn't get the locations", Toast.LENGTH_SHORT).show();

            Log.d("TEST" , "Couldn't Load");




        }


    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setSmallestDisplacement(DISTANCE);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    private void BuildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity() )
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    private boolean CheckPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if(resultCode != ConnectionResult.SUCCESS)
        {
            if(GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode,getActivity() ,PLAY_SERVICES_RES_REQUEST).show();
            }
            else
            {
                Toast.makeText(getActivity() , "this devices is not supported", Toast.LENGTH_SHORT).show();

            }
            return false;
        }
        return true;
    }

    private void updateList() {


        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(CounterRef, User.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<User, ListOnlineViewHolder>(options){
            @Override
            protected void onBindViewHolder(@NonNull ListOnlineViewHolder holder, int position, @NonNull final User model) {

                if(model.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                {
                    holder.txt_Email.setText( model.getEmail()+"  (me)  ");
                }else
                {
                    holder.txt_Email.setText( model.getEmail());
                }



                holder.txt_Email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!model.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                        {

                            Intent map = new Intent (getActivity()  , MapsActivity.class);
                            // حته دى اتغيرت الايرور هنااااا


                            map.putExtra("email" , model.getEmail());
                            map.putExtra("lat" ,mLastLocation.getLatitude() );
                            map.putExtra("lng" , mLastLocation.getLongitude());
                            startActivity(map);
                        }
                    }
                });




            }

            @NonNull
            @Override
            public ListOnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.user_layout , parent , false);


                return new ListOnlineViewHolder(view);
            }
        };
        adapter.startListening();
        listOnline.setAdapter(adapter);
        adapter.notifyDataSetChanged();






    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)

        {
            case MY_PERMISSION_REQUEST_CODE :
            {
                if(grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED)
                {

                    if(CheckPlayServices())
                    {
                        BuildGoogleApiClient();
                        createLocationRequest();
                        displayLocation();
                    }
                }
            }
            break;





        }

    }

    private void SetupSystems() {
        onlineref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(Boolean.class))
                {
                    CureentUserref.onDisconnect().removeValue(); // delete old value
                    //set Online User in List
                    CounterRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(new User(FirebaseAuth.getInstance().getCurrentUser().getEmail() , "Online"));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        CounterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    User user = postSnapshot.getValue(User.class);
                    Log.d("LOG" , ""+user.getEmail()+" is "+user.getStatues());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu  ,MenuInflater inflater)  {
        // inflater = getMenuInflater();
        inflater.inflate(R.menu.munue_menue, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.action_join:
                CounterRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                        setValue(new User(FirebaseAuth.getInstance().getCurrentUser().getEmail() , "Online"));
                break;

            case R.id.action_Logout:
                CureentUserref.removeValue();
                break;



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        displayLocation();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        startLocationsUpdate();

    }

    private void startLocationsUpdate() {
        if(ActivityCompat.checkSelfPermission(getActivity()  , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity()  , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;

        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient , mLocationRequest , this);

    }

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if(mGoogleApiClient!=null)
        {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        if(mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
        if(adapter != null)
            adapter.stopListening();
        super.onStop();

    }



    @Override
    public void onResume() {
        super.onResume();
        CheckPlayServices();
    }

}


