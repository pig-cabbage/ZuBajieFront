package com.example.a63577.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
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

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn=(Button)findViewById(R.id.button6);
        signUp=(Button)findViewById(R.id.button4);
        otherLogin=(Button)findViewById(R.id.button3);

        phoneNumber=(EditText) findViewById(R.id.editText2);
        password=(EditText) findViewById(R.id.editText3);

        //获取当前Activity的sharedPreference,onCreate函数之前不能调用getPreference
        preferences= getPreferences(Activity.MODE_PRIVATE);
        //创建preference的editor对象
        editor=preferences.edit();

        final String phoneNumStr=phoneNumber.getText().toString();
        final String passwordStr=password.getText().toString();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                String url=AppConfig.LOGIN_IN+"?phoneNumber="+phoneNumStr+"&password="+passwordStr;
                final Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String responseStr = response.body().string();
                        JSON json=JSON.parseObject(responseStr);
                        int i=((JSONObject) json).getByte("success");
                        int userId=((JSONObject) json).getInteger("userId");
                        if(i==0){
                            Toast toast =Toast.makeText(loginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG);
                            toast.show();
                        }else {
                            editor.putBoolean("isLogged",true);
                            editor.putInt("userId",userId);
                            Toast.makeText(loginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
