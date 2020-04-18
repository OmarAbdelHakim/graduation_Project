package com.example.mybus3_4_2020.MainFirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mybus3_4_2020.Customer.CustomerChooseActvity;
import com.example.mybus3_4_2020.Driver.DriverChooseActivity;
import com.example.mybus3_4_2020.R;

public class Main2Activity extends AppCompatActivity {

    private Button mDriver,mCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mCustomer=findViewById(R.id.customer);
        mDriver=findViewById(R.id.driver);
      mDriver.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Main2Activity.this, DriverChooseActivity.class);
              startActivity(intent);
          }
      });
        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CustomerChooseActvity.class);
                startActivity(intent);
                /*finish();
                return;*/
            }
        });
    }
}
