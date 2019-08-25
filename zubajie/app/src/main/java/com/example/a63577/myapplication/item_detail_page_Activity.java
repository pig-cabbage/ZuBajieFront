package com.example.a63577.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a63577.myapplication.Entity.Item;
import com.example.a63577.myapplication.constant.AppConfig;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    Bitmap bitmap;
    private Data data;


    private Item item;

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
System.out.println(item.getImageList().get(0)+"11111111111111111111111");
        if (item.getImageList().size() == 1) {

            Glide.with(this).load("http://"+item.getImageList().get(0)).into(item_image1);


        }
        else if (item.getImageList().size() == 2) {
            Glide.with(this).load("http://"+item.getImageList().get(0)).into(item_image1);
            Glide.with(this).load("http://"+item.getImageList().get(1)).into(item_image1);
        } else {
            Glide.with(this).load("http://"+item.getImageList().get(0)).into(item_image1);
            Glide.with(this).load("http://"+item.getImageList().get(1)).into(item_image1);
            Glide.with(this).load("http://"+item.getImageList().get(2)).into(item_image1);
        }


        more_item_image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OkHttpClient mOkHttpClient = new OkHttpClient();
                FormEncodingBuilder builder = new FormEncodingBuilder();

                SharedPreferences preferences=getPreferences(Context.MODE_PRIVATE);
                data=(Data)getApplication();
                int userId=data.getUserId();
                System.out.println(userId+"gvdsfsdsdfsdf");
                builder.add("userId",String.valueOf(userId));
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

                //敬请期待
                  tip(v);


            }
        });
    }


    public void tip(View view) {
        Toast toast =Toast.makeText(this,"敬请期待",Toast.LENGTH_SHORT);
        //参数1：当前的上下文环境。可用getApplicationContext()或this
        //参数2：要显示的字符串。
        //参数3：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)
        toast.setGravity(Gravity.CENTER, 500, 500);//设置提示框显示的位置

        toast.show();//显示消息
    }
    public static Bitmap getHttpBitmap(final String url) {
        final URL[] myFileUrl = {null};
        Bitmap bitmap = null;

            new Thread() {
                @Override
                public void run() {
                    //这里写入子线程需要做的工作
                    try {
                        myFileUrl[0] = new URL(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl[0].openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public Bitmap returnBitMap(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    //这是一个一步请求，不能直接返回获取，要不然永远为null
                    //在这里得到BitMap之后记得使用Hanlder或者EventBus传回主线程，不过现在加载图片都是用框架了，很少有转化为Bitmap的需求
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;

    }
    public void loadImage(View view) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this).load(url).into(item_image1);
    }

}

