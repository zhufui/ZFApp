package com.example.baseutils;

import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zf on 17-7-20.
 */

public class ActivityHelper {
    private List<Activity> activityList;
    private static ActivityHelper instance;

    private ActivityHelper() {}

    public static ActivityHelper getInstance() {
        if (instance == null) {
            synchronized (ActivityHelper.class) {
                if (instance == null) {
                    instance = new ActivityHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 往list中添加Acvitivity
     * @param activity
     */
    public void addActivity(Activity activity) {
        if(null == activityList){
            activityList = new LinkedList<>();
        }
        activityList.add(activity);
    }

    /**
     * 移除list中的Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if(null == activity){
            throw new IllegalArgumentException("Activity not null");
        }

        if(null == activityList){
            return;
        }

        activityList.remove(activity);
    }

    /**
     * 移除所有Activity
     */
    public void removeAll(){
        if(null == activityList){
            return;
        }

        for(int i=0,size=activityList.size();i<size;i++){
            Activity activity = activityList.get(i);
            if(activity != null){
                activity.finish();
            }
        }
    }

    /**
     * 退出应用程序
     * @param context 上下文环境context
     */
    public void appExit(Context context) {
        try {
            removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
