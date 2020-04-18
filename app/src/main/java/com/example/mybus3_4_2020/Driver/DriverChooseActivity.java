package com.example.mybus3_4_2020.Driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mybus3_4_2020.R;

public class DriverChooseActivity extends AppCompatActivity {

    Button D_RegisterBtn,D_LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_choose);

        //init views
        D_RegisterBtn =findViewById(R.id.D_register_btn);
        D_LoginBtn =findViewById(R.id.D_login_btn);

        //handle register button click
        D_RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start RegisterActivity
                startActivity(new Intent(DriverChooseActivity.this,DriverRegisterActivity.class));
            }
        });
        //handle login button click
        D_LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start LoginActivity
                startActivity(new Intent(DriverChooseActivity.this, DriverLoginActivity.class));
            }
        });



    }
}
