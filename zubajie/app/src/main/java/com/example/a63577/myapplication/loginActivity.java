package com.example.a63577.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.constant.AppConfig;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class loginActivity extends AppCompatActivity {

    private Button signIn;
    private Button signUp;
    private Button otherLogin;

    private EditText phoneNumber;
    private EditText password;
    private Data data;

    private SharedPreferences preferences;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
             Toast.makeText(loginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG);


        }
    };
    private Handler kHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
          Toast.makeText(loginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();

            Intent intent=new Intent(loginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    };
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = (Button) findViewById(R.id.button6);
        signUp = (Button) findViewById(R.id.button4);
        otherLogin = (Button) findViewById(R.id.button3);

        phoneNumber = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);

        //获取当前Activity的sharedPreference,onCreate函数之前不能调用getPreference
        preferences = getPreferences(Context.MODE_PRIVATE);
        //创建preference的editor对象
        editor = preferences.edit();



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumStr = phoneNumber.getText().toString();
                final String passwordStr = password.getText().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                builder.add("phoneNumber",phoneNumStr);
                builder.add("password",passwordStr);
                final Request request = new Request.Builder()
                        .url(AppConfig.LOGIN_IN)
                        .post(builder.build())
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        String responseStr = response.body().string();
                        JSON json = JSON.parseObject(responseStr);
                        System.out.println(json);
                        int i = ((JSONObject) json).getByte("success");

                        if (i == 0) {
//                            application.setLogged(false);
                            Message msg = mHandler.obtainMessage();
                            mHandler.sendMessage(msg);
                            editor.putBoolean("isLogged", false);
                            data=(Data)getApplication();
                            data.setLogged(false);
                        } else {
                            data=(Data)getApplication();
                            editor.putBoolean("isLogged", true);
                            int userId = ((JSONObject) json).getInteger("userId");
                            System.out.println(userId+"csacbshjsbchsd");
                            editor.putInt("userId", userId);
                            Message msg = mHandler.obtainMessage();
                            kHandler.sendMessage(msg);
                            data.setLogged(true);
                            data.setUserId(userId);

                        }
                        editor.commit();
                    }
                });

            }
        });

        otherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
    public void register(View v) {
        Intent intent = new Intent(loginActivity.this, register.class);
        startActivity(intent);
    }


}
