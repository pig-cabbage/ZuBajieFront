package com.example.a63577.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class register extends AppCompatActivity {


    private String user_name = "???";
    private String nick_name = "???";
    private String sex = "???";
    private String Code = "???";
    private String phone_number = "???";
    private String address = "???";


    private EditText T_user_name;
    private EditText T_nick_name;
    private EditText T_Code;
    private EditText T_phone_number;
    private EditText T_address;


    private RadioGroup radioGroup_gender;

    private Button button_ok;
    private Button button_no;
    private Button select; //头像


    private SharedPreferences preferences;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(register.this,MainActivity.class);
            startActivity(intent);
        }
    };

    Bitmap bitmap;
    String imagePath;

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
                    imagePath=getImagePath(uri,null);

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
        setContentView(R.layout.activity_register);

        //设置头像
        //uri由数据库获得
//        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//        Drawable d = new BitmapDrawable(bitmap);
//        select.setBackground(d);

        button_ok = findViewById(R.id.button_ok);
        button_no = findViewById(R.id.button_no);
        select = findViewById(R.id.pictures_register);


        T_user_name = findViewById(R.id.name_register);
        T_nick_name = findViewById(R.id.nick_name_register);

        T_Code = findViewById(R.id.code_register);
        T_phone_number = findViewById(R.id.phone_register);
        T_address = findViewById(R.id.address_register);


        radioGroup_gender= (RadioGroup) this.findViewById(R.id.radioGroup_gender_register);



        //确认修改按钮
        //用户修改完数据后确认注册
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
                            case R.id.male_register: {
                                sex[0] = 0;
                                break;
                            }
                            case R.id.female_register:{
                                sex[0]=1;
                                break;
                            }
                        }
                    }
                });
                String Code=T_Code.getText().toString();
                String phoneNumber=T_phone_number.getText().toString();
                String address=T_address.getText().toString();
                OkHttpClient mOkHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                builder.add("userName",userName);
                builder.add("nickName",nickName);
                builder.add("sex",String.valueOf(sex[0]));
                builder.add("phoneNumber",phoneNumber);
                builder.add("password",Code);
                builder.add("address",address);
                builder.add("headPortrait",imagePath);



                final Request request = new Request.Builder()
                        .url(AppConfig.LOGINUP)
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
                        JSON json = JSON.parseObject(responseStr);
                        int i = ((JSONObject) json).getByte("success");
                        if(i==1) {
                            Message msg = mHandler.obtainMessage();

                            mHandler.sendMessage(msg);
                        }
                    }

                });


            }
        });

        //取消返回上一层
        button_no.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(register.this,loginActivity.class);
                startActivity(intent);

            }
        });
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.
                        Media.DATA));
            }
            cursor.close();
        }
        Log.i(path, "相册选择");
        return path;
    }


}
