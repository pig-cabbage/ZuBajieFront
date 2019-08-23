package com.example.a63577.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;
import com.example.a63577.myapplication.constant.Data;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;




public class item_detail_page_Activity extends AppCompatActivity {
    private ImageView person_image;
    private TextView person_ID;
    private TextView time;
    private TextView price;
    private TextView item_detail_information;
    private ImageView item_image1;
    private ImageView item_image2;
    private ImageView item_image3;
    private Button more_item_image;
    private Button collect;
    private Button want_to_bollow;

    private Item item;
    final Data app = new Data();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AlertDialog alertDialog1 = new AlertDialog.Builder(item_detail_page_Activity.this)
                    .setTitle("收藏成功！")//标题
                    .setIcon(R.drawable.success_collect)//图标
                    .create();
            alertDialog1.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_page);

        person_image=(ImageView)findViewById(R.id.person_image);
        person_ID=(TextView) findViewById(R.id.person_ID);
        time=(TextView) findViewById(R.id.time);
        price=(TextView) findViewById(R.id.price);
        item_detail_information=(TextView) findViewById(R.id.item_detail_information);
        item_image1=(ImageView)findViewById(R.id.item_image1);
        item_image2=(ImageView)findViewById(R.id.item_image2);
        item_image3=(ImageView)findViewById(R.id.item_image3);
        more_item_image=(Button) findViewById(R.id.more_item_image);
        collect=(Button) findViewById(R.id.collect);
        want_to_bollow=(Button) findViewById(R.id.want_to_bollow);

        final Intent intent=getIntent();
        item =(Item)intent.getSerializableExtra("item") ;

        price.setText(item.getPrice());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        time.setText(sdf.format(item.getCreateTime()));
        item_detail_information.setText(item.getDescription());

        if (item.getImageList().size() == 1)
            item_image1.setImageResource(R.drawable.image1);
        else if (item.getImageList().size() == 2) {
            item_image1.setImageResource(R.drawable.image1);
            item_image2.setImageResource(R.drawable.image1);
        } else {

            item_image1.setImageResource(R.drawable.image1);
            item_image2.setImageResource(R.drawable.image1);
            item_image3.setImageResource(R.drawable.image1);
        }


        more_item_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();
                app.onCreate();
                builder.add("userId",String.valueOf(app.getUserId()));
                builder.add("goodId",String.valueOf(item.getGoodId()));

                final Request request = new Request.Builder()
                        .url(AppConfig.ADD_COLLECT)
                        .post(builder.build())
                        .build();

                Call call=mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {

                    @Override
                    public void onFailure(Request request, IOException e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        System.out.println("进入成功");
                        String responseStr=response.body().string();
                        System.out.println("222222");
                        System.out.println(responseStr);
                        Message msg = Message.obtain();
                        mHandler.sendMessage(msg);
                        System.out.println("5555555");
                    }
                });
            }


        });
        want_to_bollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
    }


}

