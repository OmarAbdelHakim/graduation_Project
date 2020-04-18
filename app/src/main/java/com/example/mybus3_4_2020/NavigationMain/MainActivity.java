package com.example.mybus3_4_2020.NavigationMain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mybus3_4_2020.Fragement.Customer.ChatFragment;
import com.example.mybus3_4_2020.Fragement.Customer.MessageFragment;
import com.example.mybus3_4_2020.Fragement.Customer.ProfileFragment;
import com.example.mybus3_4_2020.Fragement.Driver.SecondFragmentMessage;
import com.example.mybus3_4_2020.MainFirst.Main2Activity;
import com.example.mybus3_4_2020.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference reference;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dy mohma
         toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
     /*   FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(FirebaseAuth.getInstance().getCurrentUser().getEmail() , "online")); */



        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this , drawer , toolbar,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 3shan ashoof default al home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                new SecondFragmentMessage()).commit();


        updateNavHeader();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_message :



                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new SecondFragmentMessage()).commit();
                break;

            case R.id.nav_chat :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new ChatFragment()).commit();
                toolbar.setTitle("Line");
                break;

            case R.id.nav_profile :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new ProfileFragment()).commit();
                toolbar.setTitle("Profile");

                break;

            case R.id.nav_send :
                Toast.makeText(this, "I DO IT  ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_Share :
                Toast.makeText(this, "NEVER GIVE UP  ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout :

                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getApplicationContext() , Main2Activity.class);
                startActivity(loginActivity);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }


    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else { // navigation is not open then will do this
            super.onBackPressed();
        }
    }
    public void updateNavHeader()
    {

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_email);
        ImageView navImage = headerView.findViewById(R.id.nav_user_photo);
        navUserMail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());
       Glide.with(this).load(currentUser.getPhotoUrl()).into(navImage);




    }


}

