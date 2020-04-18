package com.example.mybus3_4_2020.NavigationMain;

import com.google.firebase.auth.FirebaseUser;

public class user {

    String image , name;

    public user(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public user(FirebaseUser currentUser) {
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName(String name) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
