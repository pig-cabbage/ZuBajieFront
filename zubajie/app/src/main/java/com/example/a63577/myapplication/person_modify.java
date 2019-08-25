package com.example.a63577.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.a63577.myapplication.constant.AppConfig;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;


public class person_modify extends AppCompatActivity {


    private String user_name = "???";
    private String nick_name = "???";
    private String sex = "???";
    private String student_number = "???";
    private String phone_number = "???";
    private String address = "???";
    private String school = "???";


    private EditText T_user_name;
    private EditText T_nick_name;
    private EditText T_sex;
    private EditText T_student_number;
    private EditText T_phone_number;
    private EditText T_address;
    private EditText T_school;
    private Data application;


    private RadioGroup radioGroup_gender;

    private Button button_ok;
    private Button button_no;
    private ImageView select; //头像


    private SharedPreferences preferences;

    Bitmap bitmap;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            HashMap<String,Object> object=(HashMap<String, Object>)msg.obj;
            T_user_name.setText((String)object.get("userName"));
            T_nick_name.setText((String)object.get("nicakName"));

            T_student_number.setText((String)object.get("studentNumber"));
            T_phone_number.setText((String)object.get("phoneNumber"));
            Glide.with(person_modify.this).load("http://"+(String)object.get("headXiang")).into(select);
        }
    };

    public void select(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                // uri转bitmap
                try {
                    // 读取uri所在的图片
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                } catch (Exception e) {

                    e.printStackTrace();
                }
                //bitmap转Drawable


                //设置图片宽高
                Drawable d = new BitmapDrawable(bitmap);
                select.setBackground(d);


            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_modify);




        button_ok = findViewById(R.id.button_ok);
        button_no = findViewById(R.id.button_no);
        select = findViewById(R.id.pictures);


        T_user_name = findViewById(R.id.name);
        T_nick_name = findViewById(R.id.nick_name);

        T_student_number = findViewById(R.id.student_number);
        T_phone_number = findViewById(R.id.phone);
        T_address = findViewById(R.id.address);


        radioGroup_gender= (RadioGroup) this.findViewById(R.id.radioGroup_gender);


        //获取userId
        application=(Data)getApplication();
        int userId=application.getUserId();
        String userIdStr=String.valueOf(userId);
        OkHttpClient okHttpClient=new OkHttpClient();
        String url= AppConfig.GET_USER_INFO.concat("?userId=").concat(userIdStr);
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
                String userName=((JSONObject) userInfo).getString("userName");
                String nicakName=((JSONObject) userInfo).getString("nicakName");
                String headXiang=((JSONObject) userInfo).getString("headPortrait");
                int sexInt=((JSONObject) userInfo).getByte("sex");
                String sex="男";
                if(sexInt==0) sex="男";
                else sex="女";
                String studentNumber=((JSONObject) userInfo).getString("studentNumber");
                String phoneNumber=((JSONObject) userInfo).getString("phoneNumber");
                Integer score=((JSONObject) userInfo).getInteger("score");
                Message msg = mHandler.obtainMessage();
                HashMap<String,Object>chuanDI=new HashMap<>();
                chuanDI.put("userName",userName);
                chuanDI.put("nicakName",nicakName);

                chuanDI.put("studentNumber",studentNumber);
                chuanDI.put("phoneNumber",phoneNumber);
                chuanDI.put("headXiang",headXiang);

                msg.obj = chuanDI;

                mHandler.sendMessage(msg);


            }
        });


        //确认修改按钮
        //用户修改完数据后确认上传
        button_ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String userName=T_user_name.getText().toString();
                String nickName=T_nick_name.getText().toString();
                final int[] sex = new int[1];
                radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        //得到用户选中的 RadioButton 对象
                        RadioButton radioButton_checked= (RadioButton) group.findViewById(checkedId);
                        switch (checkedId){
                            case R.id.male: {
                                sex[0] = 0;
                                break;
                            }
                            case R.id.female:{
                                sex[0]=1;
                                break;
                            }
                        }
                    }
                });
                String studentNumber=T_student_number.getText().toString();
                String phoneNumber=T_phone_number.getText().toString();
                String address=T_address.getText().toString();
                OkHttpClient mOkHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                builder.add("userName",userName);
                builder.add("nickName",nickName);
                builder.add("sex",String.valueOf(sex[0]));
                builder.add("studentNumber",studentNumber);
                builder.add("phoneNumber",phoneNumber);
                builder.add("address",address);
                final Request request = new Request.Builder()
                        .url(AppConfig.MODIFY_USER_INFO)
                        .post(builder.build())
                        .build();
                Call call = mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String responseStr = response.body().string();
                        JSON json=JSON.parseObject(responseStr);
                        Integer res=((JSONObject) json).getInteger("success");
                        if(res==1){
                            Toast.makeText(person_modify.this,"修改成功",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });

        //取消返回上一层
        button_no.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(person_modify.this,personal.class);
                startActivity(intent);

            }
        });
    }




}
