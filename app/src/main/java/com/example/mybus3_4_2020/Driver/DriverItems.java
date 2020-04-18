package com.example.mybus3_4_2020.Driver;

public class DriverItems {
    private String text , subText , Line  ;
    private boolean expandable ;

    public DriverItems() {
    }

    public DriverItems(String text, String subText, String line, boolean expandable) {
        this.text = text;
        this.subText = subText;
        Line = line;
        this.expandable = expandable;
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

    public String getLine() {
        return Line;
    }

    public void setLine(String line) {
        Line = line;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
