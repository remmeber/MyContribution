package com.example.rhg.outsourcing.model;

import android.widget.TextView;

/**
 * Created by remember on 2016/5/3.
 */
public class HeaderTypeModel {
    private String text;
    private int color;

    public HeaderTypeModel(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
