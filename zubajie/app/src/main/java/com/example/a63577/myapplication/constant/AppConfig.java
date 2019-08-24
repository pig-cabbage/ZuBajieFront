package com.example.a63577.myapplication.constant;

public class AppConfig {

    public final static String BASE_URL_PATH="http://192.168.0.103:8080";
    public final static String DISPLAY_ORDER = BASE_URL_PATH.concat("/displayorderbyuserid");
    public  static String DISPLAY_ITEM = BASE_URL_PATH.concat("/displayBorrowGoods");

    public static  String SHAIXUAN_BY_TAG=BASE_URL_PATH.concat("/displayBorrowByTag");
    public final static String SEARCH_BY_NAME=BASE_URL_PATH.concat("/search");
    public final static String ADD_COLLECT=BASE_URL_PATH.concat("/addcollect");
    public final static  String DIS_COLLECT=BASE_URL_PATH.concat("/displayCollect");

    public final static String ADD_MORE_STUDENTS = BASE_URL_PATH.concat("/add_more_students");
    public final static String GET_TOKEN = BASE_URL_PATH.concat("/upload/getToken");
    public final static String LOGIN_IN = BASE_URL_PATH.concat("/login");
    public final static String GET_USER_INFO= BASE_URL_PATH.concat("/getUserInfo");
    public final static String LOGINUP=BASE_URL_PATH.concat("/loginup");

    public final static String MODIFY_USER_INFO= BASE_URL_PATH.concat("/modifyUserInformation");

}
