package com.example.mybus3_4_2020.Fragement.Driver;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybus3_4_2020.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragmentProfile extends Fragment {



    private EditText mNameField, mPhoneField,mCarField;
    private Button bBack, mConfirm;


    private ImageView mProfileImage;


    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase;
    private StorageReference filePath;

    private String userID;
    private String mName;
    private String mPhone;
    private String mCar;
    private String mProfileImageUrl;

    private Uri resultUri;
    Context context;

    public ThirdFragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third_profile , container , false);



        mNameField  =  v. findViewById(R.id.name);
        mPhoneField =  v. findViewById(R.id.phone);
        mCarField   =  v. findViewById(R.id.car);



        mProfileImage = v.findViewById(R.id.profileImage);
        context = Objects.requireNonNull(getActivity()).getApplicationContext();



        mConfirm = v.findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        Log.e("Omar",userID);
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);

        getUserInfo();

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNameField.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mPhoneField.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
              /*  if(mCarField.getText().toString().isEmpty()){
                    Toast.makeText(DriverSettingsActivity.this, "please enter bus line", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                if(resultUri == null ){
                    Toast.makeText(getActivity(), "please enter image", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveUserInformation();
                Toast.makeText(getActivity(), "information edited", Toast.LENGTH_SHORT).show();

            }
        });




        mConfirm.setVisibility(View.GONE);

        return v;
    }

    private void getUserInfo() {
        mDriverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                    if (map.get("name") != null) {
                        mName = map.get("name").toString();
                        mNameField.setText(mName);
                    }
                    if (map.get("phone") != null) {
                        mPhone = map.get("phone").toString();
                        mPhoneField.setText(mPhone);
                    }
                    // To add data of car belong to driver step...> 2  -V-(20)
                    if (map.get("car") != null) {
                        mCar = map.get("car").toString();
                        mCarField.setText(mCar);
                    }
                    if (map.get("image") != null) {
                        mProfileImageUrl = map.get("image").toString();
                        Glide.with(context.getApplicationContext()).load(mProfileImageUrl).into(mProfileImage);

                    }
                }
                mConfirm.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void saveUserInformation() {
        mName = mNameField.getText().toString();
        mPhone = mPhoneField.getText().toString();
        mCar = mCarField.getText().toString(); // Step number 4 in V..> (20)

        //Toast.makeText(CustomerSettingsActivity.this,"Information Confirmed",Toast.LENGTH_LONG).show();
        filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID+".png");
        filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Uri uriDownUrl = task.getResult();
                            mProfileImage.setImageURI(resultUri);
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("name",mName);
                            userInfo.put("phone",mPhone);
                            userInfo.put("car",mCar);    //Step number 3 in V..> (20)
                            userInfo.put("image", uriDownUrl.toString());
                            mDriverDatabase.updateChildren(userInfo);
                        }
                    });
                }
            }
        });






    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //final Uri imageUri = data.getData();
            resultUri = Objects.requireNonNull(data).getData();

            if (resultUri != null) {
                // saveUserInformation();
                mProfileImage.setImageURI(resultUri);

            }

        }
    }





    }

