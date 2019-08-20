package com.example.a63577.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class order extends AppCompatActivity {

    private RecyclerView item_display_order;
    List<Item> mlist = new ArrayList<>() ;
    order_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);



        item_display_order=(RecyclerView) findViewById(R.id.item_display);


        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_order.setLayoutManager(layoutManager);
        inititem();
        adapter=new order_adapter(mlist);
        item_display_order.setAdapter(adapter);
    }

    private void inititem(){
        mlist.clear();
        ArrayList h=new ArrayList();
        h.add(R.drawable.image1);
        Item item1=new Item("电脑","这是一部很强大的电脑。",h,"10块每天","a","a",0);
        for(int a=0;a<10;a++){mlist.add(item1);}

    }
}
