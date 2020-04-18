package com.example.mybus3_4_2020.NavigationMain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.example.mybus3_4_2020.Fragement.Customer.StudentFragment;
import com.example.mybus3_4_2020.Fragement.Driver.FirstFragmentChat;
import com.example.mybus3_4_2020.Fragement.Driver.SecondFragmentMessage;
import com.example.mybus3_4_2020.Fragement.Driver.ThirdFragmentProfile;
import com.example.mybus3_4_2020.MainFirst.Main2Activity;
import com.example.mybus3_4_2020.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AnotherMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference reference;
   // private String mProfileImageUrl;
    private DatabaseReference mDriverDatabase;
    private String userID;

    private String mName;
    private String mPhone;
    private String mCar;
    private String mProfileImageUrl;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_main);
        //dy mohma
 toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Home");

        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout2);







        NavigationView navigationView = findViewById(R.id.nav_view2);
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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2 ,
                new SecondFragmentMessage()).commit();


        updateNavHeader();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_message2 :

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2 ,
                        new SecondFragmentMessage()).commit();

                break;

            case R.id.nav_chat2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2 ,
                        new FirstFragmentChat()).commit();
                toolbar.setTitle("Line");
                break;

            case R.id.nav_profile2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2 ,
                        new ThirdFragmentProfile()).commit();
                toolbar.setTitle("Profile");

                break;

            case R.id.nav_send2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2 ,
                        new StudentFragment()).commit();
                toolbar.setTitle("Students");
                break;

            case R.id.nav_Share2 :
                Toast.makeText(this, "Under Work  ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout2 :

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
    public void updateNavHeader() {

        NavigationView navigationView2 = findViewById(R.id.nav_view2);
        View headerView2 = navigationView2.getHeaderView(0);
        TextView navUserName2 = headerView2.findViewById(R.id.nav_username2);
        TextView navUserMail2 = headerView2.findViewById(R.id.nav_user_email2);
        ImageView navImage2 = headerView2.findViewById(R.id.nav_user_photo2);
        navUserMail2.setText(currentUser.getEmail());
       navUserName2.setText(currentUser.getDisplayName());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(navImage2);






        //Step number 3 in V..> (20)



    }}






