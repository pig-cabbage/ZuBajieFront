package com.example.a63577.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.Entity.AndroidOrder;
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

public class order extends AppCompatActivity {

    private RecyclerView item_display_order;
    private List<AndroidOrder> mlist = new ArrayList<>() ;
    private order_adapter adapter;
    final Data app = (Data)getApplication();
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mlist= (List<AndroidOrder>)msg.obj;
            if(null != mlist){
                adapter=new order_adapter(mlist);
                item_display_order.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        System.out.println("111111111");

        item_display_order=(RecyclerView) findViewById(R.id.item_display);


        GridLayoutManager layoutManager =new GridLayoutManager(this,1);
        item_display_order.setLayoutManager(layoutManager);
        mlist.clear();
        System.out.println("1111");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId","1");

        final Request request = new Request.Builder()
                .url(AppConfig.DISPLAY_ORDER)
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
                List<AndroidOrder> orderEntitiest = new ArrayList<>();
                orderEntitiest= JSONObject.parseArray(responseStr,AndroidOrder.class);
                System.out.println("222222");
                Message msg = mHandler.obtainMessage();
                msg.obj = orderEntitiest;
                mHandler.sendMessage(msg);

            }

        });


    }

//    private void inititem(){
//        mlist.clear();
//        System.out.println("1111");
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("userId","1");
//
//        final Request request = new Request.Builder()
//                .url(AppConfig.DISPLAY_ORDER)
//                .post(builder.build())
//                .build();
//
//        Call call=mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Request request, IOException e) {
//                System.out.println(e.toString());
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                System.out.println("进入成功");
//
//                String responseStr=response.body().string();
//                mlist= JSONObject.parseArray(responseStr,AndroidOrder.class);
//                System.out.println(mlist);
//                System.out.println("222222");
//
//            }
//        });
//
//
//    }
//    private  void showItem(List<AndroidOrder>list,RecyclerView view){
//        adapter=new order_adapter(list);
//        view.setAdapter(adapter);
//    }
}
