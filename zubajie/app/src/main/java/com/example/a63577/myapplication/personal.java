package com.example.a63577.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.constant.AppConfig;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class personal extends AppCompatActivity {

    private String user_id = "???";
    private String user_name = "???";
    private String nick_name = "???";
    private String sex = "???";
    private String student_number = "???";
    private String phone_number = "???";
    private String address = "???";
    private String score = "???";
    private String school = "???";

    private TextView T_user_id;
    private TextView T_user_name;
    private TextView T_nick_name;
    private TextView T_sex;
    private TextView T_student_number;
    private TextView T_phone_number;
    private TextView T_address;
    private TextView T_score;
    private TextView T_school;

    private Button button_auth;
    private Button button_modify;

    private SharedPreferences preferences;

    Bitmap bitmap;  //图片
    Uri head_porstraint; //头像
    ImageView head_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        button_auth = findViewById(R.id.button_auth);
        button_modify = findViewById(R.id.button_modify);

   //设置头像
        //uri由数据库获得
//        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//        Drawable d = new BitmapDrawable(bitmap);
//        head_picture.setBackground(d);

        T_user_id = findViewById(R.id.user_id);
        T_user_name = findViewById(R.id.name);
        T_nick_name = findViewById(R.id.nick_name);
        T_sex = findViewById(R.id.sex);
        T_student_number = findViewById(R.id.student_number);
        T_phone_number = findViewById(R.id.phone);
        T_address = findViewById(R.id.address);
        T_score = findViewById(R.id.score);
        T_school =findViewById(R.id.school);

        //获取userId
        preferences=getPreferences(Activity.MODE_PRIVATE);
        int userId=preferences.getInt("userId",0);

        OkHttpClient okHttpClient=new OkHttpClient();
        String url= AppConfig.GET_USER_INFO.concat(user_id);
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
                JSON userInfo=((JSONObject) json).getJSONObject("userInfo");
                Integer userId=((JSONObject) userInfo).getInteger("userId");
                String userName=((JSONObject) userInfo).getString("userName");
                String nicakName=((JSONObject) userInfo).getString("nicakName");
                int sexInt=((JSONObject) userInfo).getByte("sex");
                String sex="男";
                if(sexInt==0) sex="男";
                else sex="女";
                String studentNumber=((JSONObject) userInfo).getString("studentNumber");
                String phoneNumber=((JSONObject) userInfo).getString("phoneNumber");
                Integer score=((JSONObject) userInfo).getInteger("score");
                String school="华南理工大学";
                T_user_name.setText(userName);
                T_user_id.setText(userId);
                T_nick_name.setText(nicakName);
                T_sex.setText(sex);
                T_student_number.setText(studentNumber);
                T_phone_number.setText(phoneNumber);
                T_score.setText(score);
                T_school.setText(school);
            }
        });


        button_modify.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(personal.this,person_modify.class);
                startActivity(intent);
            }
        });

        button_auth.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(personal.this,authentication.class);
                startActivity(intent);
            }
        });
    }


}
