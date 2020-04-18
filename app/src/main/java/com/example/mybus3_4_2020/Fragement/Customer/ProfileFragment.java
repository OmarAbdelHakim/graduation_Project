package com.example.mybus3_4_2020.Fragement.Customer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mybus3_4_2020.R;
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
public class ProfileFragment extends Fragment {



    private EditText mNameField, mPhoneField;
    private Button mBack, mConfirm;


    private ImageView mProfileImage;


    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private StorageReference filePath;

    private String userID;
    private String mName;
    private String mPhone;
    private String mProfileImageUrl;
    private String dProfileImageUrl;



    private Uri resultUri;

    Context context;

    // and al 3mlha
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile , container , false);


        mNameField = v.findViewById(R.id.name);
        mPhoneField = v.findViewById(R.id.phone);

        mProfileImage = v.findViewById(R.id.profileImage);


        mBack = v.findViewById(R.id.back);
        mConfirm = v.findViewById(R.id.confirm);
        context = Objects.requireNonNull(getActivity()).getApplicationContext();

        mAuth = FirebaseAuth.getInstance();
        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        Log.e("Omar",userID);
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(userID);

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
                if(resultUri == null ){
                    Toast.makeText(getActivity(), "please enter image", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveUserInformation();
                Toast.makeText(getActivity(), "is Saved", Toast.LENGTH_SHORT).show();

            }
        });

       /* mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fi();
                return;
            }
        });*/

        mConfirm.setVisibility(View.GONE);

        return v;
    }

    private void getUserInfo() {
        mCustomerDatabase.addValueEventListener(new ValueEventListener() {
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
                    if (map.get("image") != null) {
                        mProfileImageUrl = map.get("image").toString();
                        Glide.with(context.getApplicationContext()).load(mProfileImageUrl).into(mProfileImage);
                        dProfileImageUrl = map.get("image").toString();
                        Glide.with(context.getApplicationContext()).load(dProfileImageUrl).into(mProfileImage);



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

        //Toast.makeText(CustomerSettingsActivity.this,"Information Confirmed",Toast.LENGTH_LONG).show();
        filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(userID+".png");
        filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    Objects.requireNonNull(task.getResult()).getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Uri uriDownUrl = task.getResult();
                            mProfileImage.setImageURI(resultUri);
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("name", mName);
                            userInfo.put("phone", mPhone);
                            userInfo.put("image", Objects.requireNonNull(uriDownUrl).toString());
                            mCustomerDatabase.updateChildren(userInfo);

                        }
                    });
                }
            }
        });






    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Omar","OnActivty");
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //final Uri imageUri = data.getData();
            resultUri = data.getData();
            Log.e("Omar",resultUri.toString());
            if (resultUri != null) {
                // saveUserInformation();
                mProfileImage.setImageURI(resultUri);
                Log.e("Omar","OnActivity ResultURI");
            }

        }
    }
}








