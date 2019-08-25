package com.example.a63577.myapplication;

import android.app.Application;

public class Data extends Application {
    private Integer userId=0;
    private boolean isLogged=false;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}

