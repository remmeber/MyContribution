package com.example.rhg.outsourcing.model;

/**
 * Created by remember on 2016/5/3.
 */
public class FavorableTypeModel {
    private String text;
    private int color;

    public FavorableTypeModel(String text, int color) {
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
