package com.example.zubajie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
private EditText item_information;
    private Button search;
    private Button message;
    private Button bollow;
    private Button loan;
    private ListView item_display;
    private Button first_page;
    private Button add;
    private Button mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     item_information=(EditText)   findViewById(R.id.item_information);
        search=(Button)findViewById(R.id.search);
        message=(Button)findViewById(R.id.message);
        bollow=(Button)findViewById(R.id.bollow);
        loan=(Button)findViewById(R.id.loan);
        item_display=(ListView) findViewById(R.id.item_display);
        first_page=(Button)findViewById(R.id.first_page);
        add=(Button)findViewById(R.id.add);
        mine=(Button)findViewById(R.id.mine);

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String information =item_information.getText().toString();
            }
        });
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        bollow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        loan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        first_page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        mine.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }

}
