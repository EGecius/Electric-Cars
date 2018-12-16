package com.egecius.electriccars.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import com.egecius.electriccars.retrofit.MockWebSeverInitializer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyJavaApplication extends Application {

    MockWebSeverInitializer mockWebSeverInitializer = new MockWebSeverInitializer();

    @Override
    public void onCreate() {
        super.onCreate();

        mockWebSeverInitializer.init();

        printMockWebServerUrl();
    }

    @SuppressLint("CheckResult")
    private void printMockWebServerUrl() {
        Log.v("Eg:MyJavaApplication:27", "printMockWebServerUrl");

        getMockServerUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String url) throws Exception {
                        Log.i("Eg:MyJavaApplication:29", "accept url " + url);
                    }
                });
    }

    private Single<String> getMockServerUrl() {
        return mockWebSeverInitializer.getUrl();
    }

}
