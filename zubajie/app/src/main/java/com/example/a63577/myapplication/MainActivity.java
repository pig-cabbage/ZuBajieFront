package com.example.a63577.myapplication;

import android.content.Intent;

import android.os.Handler;
import android.os.Message;

import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.alibaba.fastjson.JSONObject;

import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.github.clans.fab.FloatingActionButton;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    Item_adapter adapter;
    List<Item> mlist = new ArrayList<>();
    private RecyclerView item_display;
    private EditText item_information;
    private Button search;
    private Button message;

    private Button first_page;
    private Button add;
    private Button mine;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mlist = (List<Item>) msg.obj;
            if (null != mlist) {
                adapter = new Item_adapter(mlist);
                item_display.setAdapter(adapter);
            }
        }
    };


    private Button jie_ru;
    private Button jie_chu;
    private Button pick;
    private Button dian_zi_chan_pin;
    private Button yue_qi;
    private Button shu_ji;

    private SwipeRefreshLayout swipe_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        item_information = (EditText) findViewById(R.id.item_information);
        search = (Button) findViewById(R.id.search);
        message = (Button) findViewById(R.id.message);
        jie_chu = (Button) findViewById(R.id.jie_chu);
        jie_ru = (Button) findViewById(R.id.jie_ru);

        FloatingActionButton fab_borrow = (FloatingActionButton) findViewById(R.id.borrow); //借入

        FloatingActionButton fab_loan = (FloatingActionButton) findViewById(R.id.loan); //借出

        fab_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, release_Activity.class);
                intent.putExtra("borrow_or_loan", "借入");
                startActivity(intent);

            }
        });

        fab_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, release_Activity.class);
                intent.putExtra("borrow_or_loan", "借出");
                startActivity(intent);

            }
        });

        item_display = (RecyclerView) findViewById(R.id.item_display);
        pick = (Button) findViewById(R.id.pick);
        dian_zi_chan_pin = (Button) findViewById(R.id.dian_zi_chan_pin);
        shu_ji = (Button) findViewById(R.id.shu_ji);
        yue_qi = (Button) findViewById(R.id.yue_qi);
        first_page = (Button) findViewById(R.id.first_page);
        add = (Button) findViewById(R.id.add);
        mine = (Button) findViewById(R.id.mine);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);


        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe_refresh.setRefreshing(false);
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        item_display.setLayoutManager(layoutManager);
        System.out.println("1111");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();


        final Request request = new Request.Builder()
                .url(AppConfig.DISPLAY_ITEM)
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

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String information = item_information.getText().toString();
                searchByName(information);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        jie_ru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppConfig.DISPLAY_ITEM = AppConfig.BASE_URL_PATH.concat("/displayBorrowGoods");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        jie_chu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppConfig.DISPLAY_ITEM = AppConfig.BASE_URL_PATH.concat("/displayLendGoods");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dian_zi_chan_pin.getVisibility() == View.GONE) {
                    button_visible();
                } else button_invisible();
            }
        });

        shu_ji.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_invisible();
                //这里进行书籍筛选操作
                shaixuanByTag("书籍");
            }
        });
        dian_zi_chan_pin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_invisible();
                shaixuanByTag("电子产品");
            }
        });
        yue_qi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_invisible();
                shaixuanByTag("乐器");
            }
        });
        first_page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MYActivity.class);
                startActivity(intent);
            }
        });
    }


    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //这里执行刷新逻辑，也就是刷新 mlist 的内容

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void button_invisible() {
        dian_zi_chan_pin.setVisibility(View.GONE);
        shu_ji.setVisibility(View.GONE);
        yue_qi.setVisibility(View.GONE);
    }

    private void button_visible() {
        dian_zi_chan_pin.setVisibility(View.VISIBLE);
        shu_ji.setVisibility(View.VISIBLE);
        yue_qi.setVisibility(View.VISIBLE);
    }
    //调用后端的执行筛选功能的API
    private void shaixuanByTag(String tag) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("tag", tag);
        if (AppConfig.DISPLAY_ITEM.contains("Lend")) {
            AppConfig.SHAIXUAN_BY_TAG = AppConfig.BASE_URL_PATH.concat("/displayLendByTag");
        } else {
            AppConfig.SHAIXUAN_BY_TAG = AppConfig.BASE_URL_PATH.concat("/displayBorrowByTag");
        }

        final Request request = new Request.Builder()
                .url(AppConfig.SHAIXUAN_BY_TAG)
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
