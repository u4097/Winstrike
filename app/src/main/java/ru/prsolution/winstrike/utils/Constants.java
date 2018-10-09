package ru.prsolution.winstrike.utils;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Constants {

    public static final String DOMAIN = "http://178.57.222.1:8000/";
    public static final String API_VERSION = "0";
    public static final String URL_REST_API = String.format("http://%s/v%s/", DOMAIN, API_VERSION);
    public static final String URL_REST = "http://178.57.222.1:8000/";

    public static final String URL_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=ru.prsolution.winstrike";
    public static final String URL_VK = "https://vk.com/winstrikearena";
    public static final String URL_INSTAGRAM = "https://www.instagram.com/winstrikearena/";
    public static final String URL_TWEETER = "https://twitter.com/winstrikearena";
    public static final String URL_FACEBOOK = "https://www.facebook.com/winstrikegg/";
    public static final String URL_TWITCH= "https://www.twitch.tv/winstrikearena";


    public static final String IMAGE_TYPE= "image/jpg";
    public static final String SHARE_DRAWABLE = "/drawable/";
    public static final String SHARE_IMG= "winstrike_share";
    public static final String SHARE_IMG_TITLE= "Send";
    public static final String ANDROID_RESOURCES_PATH= "android.resource://";


    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_TYPE_BEARER = "Bearer ";

    public static final long TIME_UPDATE =  TimeUnit.MINUTES.toMillis(5);

    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    @SuppressLint("SimpleDateFormat") public static final SimpleDateFormat SIMPLE_LOCAL_DATE_FORMAT
            = new SimpleDateFormat(SHORT_DATE_FORMAT, new Locale("ru"));

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.ZZZZ";
    @SuppressLint("SimpleDateFormat") public static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat(DATE_FORMAT);

    public static final String TIME_FORMAT = "HH:mm:ss";
    @SuppressLint("SimpleDateFormat") public static final SimpleDateFormat SIMPLE_TIME_FORMAT
            = new SimpleDateFormat(TIME_FORMAT);

    /* константы времени */
    public static final int SECOND = 1000;
    public static final int MINUTE = 60 * SECOND;
    public static final int HOUR = 60 * MINUTE;
    public static final int DAY = 24 * HOUR;
    public static final int WEEK = 7 * DAY;


    private Constants() {
    }

}