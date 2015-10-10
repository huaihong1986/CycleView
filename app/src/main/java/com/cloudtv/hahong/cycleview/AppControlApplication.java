package com.cloudtv.hahong.cycleview;


import android.app.Application;
import android.content.Context;


public class AppControlApplication extends Application {
    private static AppControlApplication sInstance;
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppControlApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        if (sApplicationContext == null) {
            sApplicationContext = sInstance.getApplicationContext();
        }
        return sApplicationContext;
    }

}
