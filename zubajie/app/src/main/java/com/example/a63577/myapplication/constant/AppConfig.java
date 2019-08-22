package com.example.a63577.myapplication.constant;

public class AppConfig {

    public final static String BASE_URL_PATH="http://192.168.0.103:8080";
    public final static String DISPLAY_ORDER = BASE_URL_PATH.concat("/displayorderbyuserid");
    public  static String DISPLAY_ITEM = BASE_URL_PATH.concat("/displayBorrowGoods");
    public final static String ADD_MORE_STUDENTS = BASE_URL_PATH.concat("/add_more_students");
    public final static String GET_TOKEN = BASE_URL_PATH.concat("/getToken");
    public final static String LOGIN_IN = BASE_URL_PATH.concat("/login");
}
