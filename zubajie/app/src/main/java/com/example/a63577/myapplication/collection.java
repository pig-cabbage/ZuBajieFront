package com.example.a63577.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.a63577.myapplication.Entity.Item;

import java.util.ArrayList;
import java.util.List;

public class collection extends AppCompatActivity {

    private RecyclerView item_display_collection;
    List<Item> mlist = new ArrayList<>() ;
    collection_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        item_display_collection=(RecyclerView) findViewById(R.id.item_display);


        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_collection.setLayoutManager(layoutManager);
        inititem();
        adapter=new collection_adapter(mlist);
        item_display_collection.setAdapter(adapter);
    }

    private void inititem(){
        mlist.clear();


    }
}
