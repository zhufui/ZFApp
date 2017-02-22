package com.example.baseutils;

import android.content.Context;

/**
 * Created by zf on 17-2-22.
 * Utils初始化
 */
public class Utils {
    private static Context context;
    private static SPUtils spUtils;
    private static final String DEFAULT_SP_NAME = "baseutils";

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        spUtils = new SPUtils(DEFAULT_SP_NAME);
    }

    public static void init(Context context, String spName) {
        Utils.context = context.getApplicationContext();
        spUtils = new SPUtils(spName);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        return context;
    }

    public static SPUtils getSpUtils() {
        return spUtils;
    }
}
