package com.example.a63577.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MYActivity extends AppCompatActivity {
    private Button first_page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        first_page=(Button)findViewById(R.id.first_page);
        first_page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
finish();
            }
        });
    }
    public void person_mes(View v){
        Intent intent=new Intent(MYActivity.this,personal.class);
        startActivity(intent);
    }

    public void btn_collection(View v) {

        if(getPreferences(Activity.MODE_PRIVATE).getBoolean("isLogged",false)==false)
        {
            Message msg = kHandler.obtainMessage();
            kHandler.sendMessage(msg);
        }

            else{
        Intent intent=new Intent(MYActivity.this,collection.class);
        startActivity(intent);}
    }

    public void btn_order(View v) {
        if(getPreferences(Activity.MODE_PRIVATE).getBoolean("isLogged",false)==false)
        {
            Message msg = kHandler.obtainMessage();
            kHandler.sendMessage(msg);
        }

        else {
            Intent intent = new Intent(MYActivity.this, order.class);
            startActivity(intent);
        }
    }
    private Handler kHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MYActivity.this, "请先登录", Toast.LENGTH_SHORT).show();

        }

    };



    public void tip(View view) {
        Toast toast =Toast.makeText(this,"敬请期待",Toast.LENGTH_SHORT);
        //参数1：当前的上下文环境。可用getApplicationContext()或this
        //参数2：要显示的字符串。
        //参数3：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)
        toast.setGravity(Gravity.CENTER, 500, 500);//设置提示框显示的位置

        toast.show();//显示消息
    }
}
