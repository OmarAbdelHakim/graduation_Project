package com.example.mybus3_4_2020.Fragement.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.mybus3_4_2020.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    Button mStudent_online_cutomer ;

    // and al 3mlha
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       // return  inflater.inflate(R.layout.fragment_message , container , false);
        View v = inflater.inflate(R.layout.fragment_message , container , false);


/*
        mStudent_online_cutomer = v.findViewById(R.id.student_online);

        mStudent_online_cutomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getActivity() , HomeActivity.class);
                startActivity(go);
            }
        });

*/

        return v;




    }
}

