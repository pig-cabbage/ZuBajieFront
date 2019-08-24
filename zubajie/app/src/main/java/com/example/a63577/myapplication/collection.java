package com.example.a63577.myapplication;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.example.a63577.myapplication.constant.Data;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class collection extends AppCompatActivity {

    private RecyclerView item_display_collection;
    List<Item> mlist = new ArrayList<>() ;
    collection_adapter adapter;

    Data app=new Data();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mlist = (List<Item>) msg.obj;
            if (null != mlist) {
                adapter = new collection_adapter(mlist);
                item_display_collection.setAdapter(adapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);



        item_display_collection=(RecyclerView) findViewById(R.id.item_display_collection);



        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_collection.setLayoutManager(layoutManager);

        app.onCreate();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId",String.valueOf(app.getUserId()));

        final Request request = new Request.Builder()
                .url(AppConfig.DIS_COLLECT)
                .post(builder.build())
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println("进入成功收藏页");
                String responseStr = response.body().string();
                List<Item> orderEntitiest = new ArrayList<>();
                orderEntitiest = JSONObject.parseArray(responseStr, Item.class);
                System.out.println("222222");
                Message msg = mHandler.obtainMessage();
                msg.obj = orderEntitiest;
                mHandler.sendMessage(msg);

            }

        });

        adapter=new collection_adapter(mlist);
        item_display_collection.setAdapter(adapter);
    }

    private void inititem(){
        mlist.clear();


    }
}
