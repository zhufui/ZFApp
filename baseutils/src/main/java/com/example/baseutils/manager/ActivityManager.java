package com.example.baseutils.manager;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by zf on 17-6-5.
 * activity管理
 * 调用方式,例如：
 * ActivityManager.getActivityManager().addActivity(this);
 */
public class ActivityManager {
    public static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {}

    public static ActivityManager getActivityManager() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * <p>描述:获取当前Activity（堆栈中最后一个压入的）</p>
     *
     * @return 当前activity对象
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * <p>描述:结束当前Activity（堆栈中最后一个压入的）)</p>
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * <p>描述:结束指定的Activity</p>
     *
     * @param activity 当前activity对象
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * <p>描述:移除当前statck中的Activity</p>
     *
     * @param activity 当前activity对象
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     *
     * <p>描述:结束指定类名的Activity</p>
     * @param cls    当前activity类对象
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     *
     * <p>描述:结束所有Activity</p>
     */
    public void finishAllActivity() {
        if(activityStack==null)return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     *
     * <p>描述:退出应用程序</p>
     * @param context    上下文环境context
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        catch (Exception e) {
        }
    }
}
