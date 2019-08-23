package com.example.a63577.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        T_user_name.setText(user_name);
        T_user_id.setText(user_id);
        T_nick_name.setText(nick_name);
        T_sex.setText(sex);
        T_student_number.setText(student_number);
        T_phone_number.setText(phone_number);
        T_score.setText(score);
        T_school.setText(school);

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
