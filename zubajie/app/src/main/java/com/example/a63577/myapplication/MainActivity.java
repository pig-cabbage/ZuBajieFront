package com.example.a63577.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import com.example.a63577.myapplication.Entity.Item;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    Item_adapter adapter;
    List<Item> mlist = new ArrayList<>() ;

    private EditText item_information;
    private Button message;
    private RecyclerView item_display;
    private  Button jie_ru;
    private  Button jie_chu;
    private  Button dian_zi_chan_pin;
    private  Button yue_qi;
    private  Button shu_ji;
    private Button first_page;
    private Button mine;
    private SwipeRefreshLayout swipe_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        item_information=(EditText)   findViewById(R.id.item_information);
        message=(Button)findViewById(R.id.message);
        jie_chu=(Button)findViewById(R.id.jie_chu);
        jie_ru=(Button)findViewById(R.id.jie_ru);

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
        dian_zi_chan_pin=(Button)findViewById(R.id.dian_zi_chan_pin);
        shu_ji=(Button)findViewById(R.id.shu_ji);
        yue_qi=(Button)findViewById(R.id.yue_qi);
        first_page=(Button)findViewById(R.id.first_page);
        mine=(Button)findViewById(R.id.mine);
        swipe_refresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh) ;

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe_refresh.setRefreshing(false);
            }
        });

        GridLayoutManager layoutManager =new GridLayoutManager(this,2);
        item_display.setLayoutManager(layoutManager);
        inititem();
        adapter=new Item_adapter(mlist);
        item_display.setAdapter(adapter);

        item_information.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //点击键盘的搜索按钮后
                    String information =item_information.getText().toString();
                    Intent intent=new Intent(MainActivity.this,search.class);
                    startActivity(intent);
                    }
                return false;    }
        });
        message.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        jie_ru.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//借入筛选
                chang_button_fenlei();

                jie_ru.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                jie_ru.setTextColor(getResources().getColor(R.color.blue));
            }
        });
        jie_chu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//借出筛选
                chang_button_fenlei();

                jie_chu.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                jie_chu.setTextColor(getResources().getColor(R.color.blue));


            }
        });

        shu_ji.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();

                shu_ji.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                shu_ji.setTextColor(getResources().getColor(R.color.blue));
                //这里进行书籍筛选操作
            }
        });
        dian_zi_chan_pin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();

                dian_zi_chan_pin.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                dian_zi_chan_pin.setTextColor(getResources().getColor(R.color.blue));
            }
        });
        yue_qi.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chang_button_fenlei();
                yue_qi.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border_2));
                yue_qi.setTextColor(getResources().getColor(R.color.blue));
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

    //列表初始化数据
    private void inititem(){
        mlist.clear();
//        ArrayList h=new ArrayList();
//        h.add(R.drawable.image1);
//        Item item1=new Item("电脑","这是一部很强大的电脑。",h,"10块每天","a","a",0);
//        for(int a=0;a<10;a++){mlist.add(item1);}
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

    private void chang_button_fenlei(){
        jie_ru.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        jie_chu.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        shu_ji.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        yue_qi.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        dian_zi_chan_pin.setBackgroundDrawable(getResources().getDrawable(R.drawable.r_border));
        jie_chu.setTextColor(getResources().getColor(R.color.black));
        shu_ji.setTextColor(getResources().getColor(R.color.black));
        dian_zi_chan_pin.setTextColor(getResources().getColor(R.color.black));
        jie_ru.setTextColor(getResources().getColor(R.color.black));
        yue_qi.setTextColor(getResources().getColor(R.color.black));
    }



}
