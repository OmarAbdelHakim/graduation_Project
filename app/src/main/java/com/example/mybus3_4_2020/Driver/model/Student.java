package com.example.mybus3_4_2020.Driver.model;

public class Student {
    private String first , fourth , second,subject , third ;
    private boolean exxx;

    public Student() {
    }

    public Student(String first, String fourth, String second, String subject, String third, boolean exxx) {
        this.first = first;
        this.fourth = fourth;
        this.second = second;
        this.subject = subject;
        this.third = third;
        this.exxx = exxx;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public boolean isExxx() {
        return exxx;
    }

    public void setExxx(boolean exxx) {
        this.exxx = exxx;
    }
}
