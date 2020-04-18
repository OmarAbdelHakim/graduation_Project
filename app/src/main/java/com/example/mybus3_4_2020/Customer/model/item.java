package com.example.mybus3_4_2020.Customer.model;

public class item {
    private String text , subText , Line;
    private boolean expandable;

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public item() {
    }

    public item(String text, String subText, boolean expandable , String Line) {
        this.text = text;
        this.subText = subText;
        this.expandable = expandable;
        this.Line = Line;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
