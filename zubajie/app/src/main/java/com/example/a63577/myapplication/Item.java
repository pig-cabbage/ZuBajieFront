package com.example.a63577.myapplication;
//这是用户发布一条记录所填写的信息的一个集合类
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private String title;
    public String text;
    public ArrayList Imageid;
    public String price;
    public String item_type;
    public String effect_time;
    public int type;//0借入 1借出

    public Item(String a,String b,ArrayList c,String d,String e,String f,int g)
    {title=a;text=b;Imageid=c;price=d;item_type=e;effect_time=f;type=g;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
