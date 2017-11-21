package com.example.android.mpermissions.permission;

import android.Manifest;

/**
 * Created by zhufu on 2017/11/20.
 */

public class Permissions {
    //电话权限
    public static final String[] PHONE = new String[]{Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,};


    //CALENDAR
    public static final String[] CALENDAR = new String[]{Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE};

    //拍照
    public static final String[] CAMERA = new String[]{Manifest.permission.CAMERA};

    //访问通讯录
    public static final String[] CONTACTS = new String[]{Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS};

    //位置信息
    public static final String[] LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    //短信权限
    public static final String[] SMS = new String[]{Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS};

    //访问sd卡权限
    public static final String[] STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

}
