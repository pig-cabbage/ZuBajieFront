package com.example.a63577.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MYActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }
    public void person_mes(View v){
        Intent intent=new Intent(MYActivity.this,authentication.class);
        startActivity(intent);
    }

    public void btn_collection(View v) {
        Intent intent=new Intent(MYActivity.this,collection.class);
        startActivity(intent);
    }

    public void btn_order(View v) {
        Intent intent=new Intent(MYActivity.this,order.class);
        startActivity(intent);
    }
}
