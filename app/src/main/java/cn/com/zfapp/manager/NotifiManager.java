package cn.com.zfapp.manager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import cn.com.zfapp.R;

/**
 * Created by zf on 17-2-16.
 * 通知管理
 */
public class NotifiManager {
    private static NotificationManager notificationManager = null;

    public synchronized static NotificationManager getNotificationManager(Context context) {
        if (notificationManager == null) {
            synchronized (NotifiManager.class) {
                if (notificationManager == null) {
                    notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                }
            }
        }
        return notificationManager;
    }

    public static final int NOTIFICATION_ID = 1;

    public static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /**
     * 创建通知
     * @param context
     * @return
     */
    public static Notification builderNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setTicker("您有一条新消息");//当有消息来时通知栏上显示的内容

        builder.setContentTitle("标题");//通知栏第一行显示的标题
        builder.setContentText("内容");//通知栏第二行显示的内容
        builder.setSubText("主题内容");//通知栏第三行显示的内容

        builder.setContentIntent(getPendingIntent(context));//设置跳转的intent

        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));//设置大图
        builder.setSmallIcon(R.drawable.ic_stat_notification);//设置小图,高于5.0系统这个图标会是白色一片,要重新设置个纯色的图标
        builder.setColor(Color.parseColor("#EAA935"));//设置小图的背景色

        builder.setAutoCancel(true);//是否允许自动取消
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);//设置来消息时有声音和振动,

        return builder.build();
    }

    /**
     * 发送通知
     * @param context
     * @param id 组id
     */
    public static void sendNotification(Context context, int id) {
        getNotificationManager(context).notify(id, builderNotification(context));
    }

    /**
     * 取消所有通知
     * @param context
     */
    public static void cancelAll(Context context) {
        getNotificationManager(context).cancelAll();
    }

    /**
     * 取消指定组的通知
     * @param context
     * @param id
     */
    public static void cancel(Context context, int id) {
        getNotificationManager(context).cancel(id);
    }
}
