package com.example.a63577.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    Item_adapter adapter;
    List<Item> mlist = new ArrayList<>() ;

    private EditText item_information;
    private Button search;
    private Button message;
    private Button bollow;
    private Button loan;
    private RecyclerView item_display;
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

//        loan=(Button)findViewById(R.id.loan);

        FloatingActionButton fab_borrow = (FloatingActionButton) findViewById(R.id.borrow);

        fab_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,release_Activity.class);
                startActivity(intent);
            }
        });

        item_display=(RecyclerView) findViewById(R.id.item_display);
        first_page=(Button)findViewById(R.id.first_page);
        add=(Button)findViewById(R.id.add);
        mine=(Button)findViewById(R.id.mine);

        GridLayoutManager layoutManager =new GridLayoutManager(this,2);
        item_display.setLayoutManager(layoutManager);
        inititem();
        adapter=new Item_adapter(mlist);
        item_display.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String information =item_information.getText().toString();
            }
        });
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

//        loan.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//            }
//        });
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
                Intent intent=new Intent(MainActivity.this,MYActivity.class);
                startActivity(intent);
            }
        });
    }
    private void inititem(){
        mlist.clear();
        ArrayList h=new ArrayList();
        h.add(R.drawable.image1);
        Item item1=new Item("电脑","这是一部很强大的电脑。",h,"10块每天","a","a",0);
        for(int a=0;a<10;a++){mlist.add(item1);}

    }

}
