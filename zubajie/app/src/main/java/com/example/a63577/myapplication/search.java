package com.example.a63577.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a63577.myapplication.Entity.Item;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    private RecyclerView item_display_search;
    List<Item> mlist = new ArrayList<>() ;
    search_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        item_display_search=(RecyclerView) findViewById(R.id.item_display_search);


        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_search.setLayoutManager(layoutManager);
        inititem();
        adapter=new search_adapter(mlist);
        item_display_search.setAdapter(adapter);
    }

    private void inititem(){
        mlist.clear();


    }


}
