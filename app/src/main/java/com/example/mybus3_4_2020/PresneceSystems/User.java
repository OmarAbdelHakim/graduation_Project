package com.example.mybus3_4_2020.PresneceSystems;

public class User {
    private String email , statues;

    public User() {
    }

    public User(String email, String statues) {
        this.email = email;
        this.statues = statues;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }
}
