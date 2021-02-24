package com.example.tdd;

import android.app.Application;

/**
 * Created by zhengwei on 2021/2/24.
 */
public class MockApplication extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Application getApplication() {
        return application;
    }
}
