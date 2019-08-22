package com.example.a63577.myapplication.constant;

import android.app.Application;

public class Data extends Application {
    private Integer userId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Override
    public void onCreate(){
        userId=1;
        super.onCreate();
    }

}

