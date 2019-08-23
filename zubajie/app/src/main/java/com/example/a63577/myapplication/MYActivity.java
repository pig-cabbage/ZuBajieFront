package com.example.a63577.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MYActivity extends AppCompatActivity {
    private Button first_page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        first_page=(Button)findViewById(R.id.first_page);
        first_page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
finish();
            }
        });
    }
    public void person_mes(View v){
        Intent intent=new Intent(MYActivity.this,personal.class);
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
