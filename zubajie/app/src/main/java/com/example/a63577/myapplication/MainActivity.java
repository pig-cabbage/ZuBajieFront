package com.example.a63577.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.Entity.AndroidOrder;
import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    Item_adapter adapter;
    List<Item> mlist = new ArrayList<>() ;
    private RecyclerView item_display;
    private EditText item_information;
    private Button search;
    private Button message;
    private Button first_page;
    private Button add;
    private Button mine;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mlist= (List<Item>)msg.obj;
            if(null != mlist){
                adapter=new Item_adapter(mlist);
                item_display.setAdapter(adapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        item_information=(EditText)   findViewById(R.id.item_information);
        search=(Button)findViewById(R.id.search);
        message=(Button)findViewById(R.id.message);


        FloatingActionButton fab_borrow = (FloatingActionButton) findViewById(R.id.borrow); //借入

        FloatingActionButton fab_loan = (FloatingActionButton) findViewById(R.id.loan); //借出

        fab_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,release_Activity.class);
                intent.putExtra("borrow_or_loan", "借入");
                startActivity(intent);

            }
        });

        fab_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,release_Activity.class);
                intent.putExtra("borrow_or_loan", "借出");
                startActivity(intent);

            }
        });

        item_display=(RecyclerView) findViewById(R.id.item_display);
        first_page=(Button)findViewById(R.id.first_page);
        add=(Button)findViewById(R.id.add);
        mine=(Button)findViewById(R.id.mine);

        GridLayoutManager layoutManager =new GridLayoutManager(this,2);
        item_display.setLayoutManager(layoutManager);
        System.out.println("1111");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();


        final Request request = new Request.Builder()
                .url(AppConfig.DISPLAY_ITEM)
                .post(builder.build())
                .build();

        Call call=mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println("进入成功");
                String responseStr=response.body().string();
                List<Item> orderEntitiest = new ArrayList<>();
                orderEntitiest= JSONObject.parseArray(responseStr,Item.class);
                System.out.println("222222");
                Message msg = mHandler.obtainMessage();
                msg.obj = orderEntitiest;
                mHandler.sendMessage(msg);

            }

        });

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String information =item_information.getText().toString();
            }
        });
        message.setOnClickListener(new View.OnClickListener(){
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
                Intent intent=new Intent(MainActivity.this,MYActivity.class);
                startActivity(intent);
            }
        });
    }



}
