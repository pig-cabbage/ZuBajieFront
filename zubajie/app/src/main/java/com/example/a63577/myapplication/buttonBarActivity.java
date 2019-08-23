package com.example.a63577.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class buttonBarActivity extends AppCompatActivity {
    private Button index;
    private Button myInfo;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttom_bar);

        index=(Button) findViewById(R.id.first_page);
        myInfo=(Button) findViewById(R.id.mine);

        preferences=getPreferences(Activity.MODE_PRIVATE);
        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(buttonBarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogged=preferences.getBoolean("isLogged",false);
                if(isLogged==true){
                    Intent intent = new Intent(buttonBarActivity.this,MYActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(buttonBarActivity.this,loginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
