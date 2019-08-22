package com.example.a63577.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class release_Activity extends AppCompatActivity {


    String borrow_or_loan; //借入或借出
    Button selectPicture;
    List<Bitmap> bitmaps; //图片的集合

    Bitmap bitmap;  //图片


    String item_type; //分类

    String item_time; //有效时长


    EditText edit;

    EditText edit_title; //标题
    EditText edit_supply; //补充说明
    EditText edit_price; //价格

    List<String> imageList;//图片路径列表

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
                String imagePath = getImagePath(uri,null);
                Toast toast =Toast.makeText(this,imagePath,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 500, 500);//设置提示框显示的位置
                toast.show();//显示消息
                // uri转bitmap
                try {
                    // 读取uri所在的图片
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    bitmaps.add(bitmap);

                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }
                //bitmap转Drawable

                LinearLayout linearLayout = findViewById(R.id.line);
                ImageView imageView = new ImageView(this);
                 //设置图片宽高
                Drawable d = new BitmapDrawable(bitmap);
                imageView.setBackground(d);

                //设为按钮的背景
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400,
                        400);//两个400分别为添加图片的大小
                imageView.setLayoutParams(params);
                linearLayout.addView(imageView);

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        Intent intent = getIntent();
        // 获取到传递过来的值
        borrow_or_loan = intent.getStringExtra("borrow_or_loan"); //传递值是借入或借出


        //获取对应的输入框
        edit_title = (EditText) findViewById(R.id.title);
        edit_supply = (EditText) findViewById(R.id.supplement);
        edit_price = (EditText) findViewById(R.id.price);


        final String[] ctype = new String[]{"全部", "资料", "电器","文具", "其他"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式

        final Spinner spinner = super.findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);

        //下面获取选择的值。
        //添加Spinner事件监听
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                item_type = ctype[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
                item_type = spinner.getSelectedItem().toString(); //获取分类
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
                    edit.setVisibility(View.GONE);;

                    //无限天数
                }
                else{
                    edit=findViewById(R.id.editText4);
                    edit.setVisibility(View.VISIBLE);

                    item_time = edit.getText().toString();  //多少天
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //上传订单的数据
    public void submit(View v){


      String  title  = edit_title.getText().toString();

      String supply = edit_supply.getText().toString();

      String price = edit_price.getText().toString();


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

    private void displayImage(String imagePath){
        if(imagePath !=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //userImage.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_LONG).show();
        }
    }
}


