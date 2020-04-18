package com.example.mybus3_4_2020.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mybus3_4_2020.R;

 public class CustomerChooseActvity extends AppCompatActivity {
    Button C_RegisterBtn,C_LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_choose_actvity);


        //init views
        C_RegisterBtn =findViewById(R.id.C_register_btn);
        C_LoginBtn =findViewById(R.id.C_login_btn);

        //handle register button click
        C_RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start RegisterActivity
                startActivity(new Intent(CustomerChooseActvity.this,CustomerRegisterActivity.class));
            }
        });
        //handle login button click
        C_LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start LoginActivity
                startActivity(new Intent(CustomerChooseActvity.this,CustomerLoginActivity.class));
            }
        });
    }
}
