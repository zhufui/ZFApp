package cn.com.zfapp;

import android.app.Application;

/**
 * Created by zf on 17-2-3.
 */
public class App extends Application {
    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
