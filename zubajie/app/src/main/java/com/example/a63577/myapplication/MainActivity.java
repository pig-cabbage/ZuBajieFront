package com.example.a63577.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
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

import static android.support.constraint.Constraints.TAG;


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
    private Button dian_zi_chan_pin;
    private Button yue_qi;
    private Button shu_ji;
    private Button zi_liao;
    private Button wen_ju;
    private Button sheng_huo;
    private Button qi_ta;

    private SwipeRefreshLayout swipe_refresh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        item_information = (EditText) findViewById(R.id.item_information);

        message = (Button) findViewById(R.id.message);
        jie_chu = (Button) findViewById(R.id.jie_chu);
        jie_ru = (Button) findViewById(R.id.jie_ru);


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

                Intent intent = new Intent(MainActivity.this, release_Activity.class);

                intent.putExtra("borrow_or_loan", "借出");
                startActivity(intent);

            }
        });


        item_display = (RecyclerView) findViewById(R.id.item_display);

        dian_zi_chan_pin = (Button) findViewById(R.id.dian_zi_chan_pin);
        shu_ji = (Button) findViewById(R.id.shu_ji);
        yue_qi = (Button) findViewById(R.id.yue_qi);
        zi_liao = (Button) findViewById(R.id.zi_liao);
        wen_ju = (Button) findViewById(R.id.wen_ju);
        sheng_huo = (Button) findViewById(R.id.sheng_huo);
        qi_ta = (Button) findViewById(R.id.qi_ta);



        first_page = (Button) findViewById(R.id.first_page);

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






        first_page.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        mine.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this, MYActivity.class);
                                    }
                                });


        item_information.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //点击键盘的搜索按钮后
                    String information =item_information.getText().toString();
                    Intent intent=new Intent(MainActivity.this,search.class);
                    startActivity(intent);
                    searchByName(information);
                    }
                return false;    }
        });
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //敬请期待
                tip(v);
            }
        });
        jie_ru.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//借入筛选
                chang_button_fenlei();

                jie_ru.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                jie_ru.setTextColor(getResources().getColor(R.color.blue));
                AppConfig.DISPLAY_ITEM = AppConfig.BASE_URL_PATH.concat("/displayBorrowGoods");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        jie_chu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//借出筛选
                chang_button_fenlei();

                jie_chu.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                jie_chu.setTextColor(getResources().getColor(R.color.blue));
                AppConfig.DISPLAY_ITEM = AppConfig.BASE_URL_PATH.concat("/displayLendGoods");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        shu_ji.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();

                shu_ji.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                shu_ji.setTextColor(getResources().getColor(R.color.blue));
                //这里进行书籍筛选操作
                shaixuanByTag("书籍");
            }
        });
        dian_zi_chan_pin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();

                dian_zi_chan_pin.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                dian_zi_chan_pin.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("电子产品");
            }
        });
        yue_qi.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                yue_qi.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                yue_qi.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("乐器");
            }
        });
        zi_liao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                zi_liao.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                zi_liao.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("资料");

            }
        });
        wen_ju.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                wen_ju.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                wen_ju.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("文具");
            }
        });
        sheng_huo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                sheng_huo.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                sheng_huo.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("生活");
            }
        });
        qi_ta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                qi_ta.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                qi_ta.setTextColor(getResources().getColor(R.color.blue));
                shaixuanByTag("其他");
            }
        });


        first_page.setOnClickListener(new View.OnClickListener(){
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

    private void chang_button_fenlei(){
        jie_ru.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        jie_chu.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        shu_ji.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        yue_qi.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        dian_zi_chan_pin.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        sheng_huo.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        zi_liao.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        wen_ju.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        qi_ta.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));

        jie_chu.setTextColor(getResources().getColor(R.color.black));
        shu_ji.setTextColor(getResources().getColor(R.color.black));
        dian_zi_chan_pin.setTextColor(getResources().getColor(R.color.black));
        jie_ru.setTextColor(getResources().getColor(R.color.black));
        yue_qi.setTextColor(getResources().getColor(R.color.black));
        sheng_huo.setTextColor(getResources().getColor(R.color.black));
        zi_liao.setTextColor(getResources().getColor(R.color.black));
        wen_ju.setTextColor(getResources().getColor(R.color.black));
        qi_ta.setTextColor(getResources().getColor(R.color.black));
    }
            private  void refresh()
            {
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


    public void tip(View view) {
        Toast toast =Toast.makeText(this,"敬请期待",Toast.LENGTH_SHORT);
        //参数1：当前的上下文环境。可用getApplicationContext()或this
        //参数2：要显示的字符串。
        //参数3：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)
        toast.setGravity(Gravity.CENTER, 500, 500);//设置提示框显示的位置

        toast.show();//显示消息
    }

}
