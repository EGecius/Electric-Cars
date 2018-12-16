package com.egecius.electriccars.app;

import android.app.Application;
import com.egecius.electriccars.retrofit.MockWebSeverInitializer;

public class MyJavaApplication extends Application {

    MockWebSeverInitializer mockWebSeverInitializer = new MockWebSeverInitializer();

    @Override
    public void onCreate() {
        super.onCreate();

        mockWebSeverInitializer.init();
    }
}
