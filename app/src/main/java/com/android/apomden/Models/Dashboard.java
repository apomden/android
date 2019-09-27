package com.android.apomden.Models;

import android.graphics.drawable.Drawable;

public class Dashboard {

    private String title, number;
    private Drawable icon, bg;

    public Dashboard(String title, String number, Drawable icon, Drawable bg) {
        this.title = title;
        this.number = number;
        this.icon = icon;
        this.bg = bg;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getBg() {
        return bg;
    }

    public void setBg(Drawable bg) {
        this.bg = bg;
    }
}
