package com.example.a63577.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class release_Activity extends AppCompatActivity {

    Button selectPicture;
    Bitmap bitmap;
    String item_type;
    String item_time;

    EditText edit;
    int day;
    //选取图片按钮
    public void select_picture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                // uri转bitmap
                try {
                    // 读取uri所在的图片
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }
                //bitmap转Drawable
                Drawable d = new BitmapDrawable(bitmap);
                selectPicture = (Button) findViewById(R.id.select_picture);
                //设为按钮的背景
                selectPicture.setBackground(d);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        final String[] ctype = new String[]{"全部", "资料", "电器","文具", "其他"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        Spinner spinner = super.findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);

        //下面获取选择的值。
        //添加Spinner事件监听
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                item_type = ctype[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        final String[] ctype2 = new String[]{"无限", "天"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype2);  //创建一个数组适配器
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        Spinner spinner2 = super.findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);

        //下面获取选择的值。
        //添加Spinner事件监听
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                item_time = ctype2[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
                if(item_time=="无限"){
                    edit=findViewById(R.id.editText4);
                    edit.setVisibility(View.GONE);
                }
                else{
                    edit=findViewById(R.id.editText4);
                    edit.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}


