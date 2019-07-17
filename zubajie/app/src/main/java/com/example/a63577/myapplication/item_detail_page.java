package com.example.a63577.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class item_detail_page extends AppCompatActivity {
    private ImageView person_image;
    private TextView person_ID;
    private TextView time;
    private TextView price;
    private TextView item_detail_information;
    private ImageView item_image1;
    private ImageView item_image2;
    private ImageView item_image3;
    private Button more_item_image;
    private Button collect;
    private Button want_to_bollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_page);
        person_image=(ImageView)findViewById(R.id.person_image);
        person_ID=(TextView) findViewById(R.id.person_ID);
        time=(TextView) findViewById(R.id.time);
        price=(TextView) findViewById(R.id.price);
        item_detail_information=(TextView) findViewById(R.id.item_detail_information);
        item_image1=(ImageView)findViewById(R.id.item_image1);
        item_image2=(ImageView)findViewById(R.id.item_image2);
        item_image3=(ImageView)findViewById(R.id.item_image3);
        more_item_image=(Button) findViewById(R.id.more_item_image);
        collect=(Button) findViewById(R.id.collect);
        want_to_bollow=(Button) findViewById(R.id.want_to_bollow);

        more_item_image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        collect.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        want_to_bollow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }
}
