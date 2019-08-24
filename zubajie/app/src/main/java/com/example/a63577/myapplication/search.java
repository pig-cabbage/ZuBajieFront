package com.example.a63577.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    private RecyclerView item_display_search;
    List<Item> mlist = new ArrayList<>() ;
    search_adapter adapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mlist = (List<Item>) msg.obj;
            if (null != mlist) {
                adapter = new Item_adapter(mlist);
                item_display_search.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        item_display_search=(RecyclerView) findViewById(R.id.item_display_search);


        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_search.setLayoutManager(layoutManager);
        final Intent intent=getIntent();
        String information =(Item)intent.getSerializableExtra("information") ;
        searchByName(information);

    }

    private void inititem(){
        mlist.clear();


    }
    //调用后端执行搜索功能的API
    private void searchByName(String goodName)     {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("goodName", goodName);
        String url;
        if (AppConfig.DISPLAY_ITEM.contains("Lend")) {
            url = AppConfig.SEARCH_BY_NAME.concat("/searchLendGoodByName");
        } else {
            url= AppConfig.SEARCH_BY_NAME.concat("/searchBorrowGoodByName");
        }

        final Request request = new Request.Builder()
                .url(url)
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
                System.out.println("进入成功");
                String responseStr = response.body().string();
                List<Item> orderEntitiest = new ArrayList<>();
                orderEntitiest = JSONObject.parseArray(responseStr, Item.class);

                System.out.println("222222");
                Message msg = mHandler.obtainMessage();
                msg.obj = orderEntitiest;
                mHandler.sendMessage(msg);

            }
        });

    }


}
