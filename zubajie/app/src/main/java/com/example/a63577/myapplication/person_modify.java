package com.example.a63577.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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


    private Button button_ok;
    private Button button_no;
    private Button select; //头像

    Bitmap bitmap;

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

        //设置头像
        //uri由数据库获得
//        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//        Drawable d = new BitmapDrawable(bitmap);
//        select.setBackground(d);

        button_ok = findViewById(R.id.button_ok);
        button_no = findViewById(R.id.button_no);
        select = findViewById(R.id.pictures);


        T_user_name = findViewById(R.id.name);
        T_nick_name = findViewById(R.id.nick_name);
        T_sex = findViewById(R.id.sex);
        T_student_number = findViewById(R.id.student_number);
        T_phone_number = findViewById(R.id.phone);
        T_address = findViewById(R.id.address);
        T_school = findViewById(R.id.school);


        //先展示原来的数据
        T_user_name.setText(user_name);
        T_nick_name.setText(nick_name);
        T_sex.setText(sex);
        T_student_number.setText(student_number);
        T_phone_number.setText(phone_number);
        T_address.setText(address);
        T_school.setText(school);


        //确认修改按钮
        //用户修改完数据后确认上传
        button_ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){






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
