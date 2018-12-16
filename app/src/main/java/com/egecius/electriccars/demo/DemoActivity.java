package com.egecius.electriccars.demo;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class DemoActivity extends AppCompatActivity {

    @Inject
    DemoActivityPresenter demoActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDemoActivityComponent.builder().build().injectInto(this);

        Log.d("Eg:DemoActivity:21", "onCreate Kotlin module & component");
        Log.i("Eg:DemoActivity:20", "onCreate demoActivityPresenter " + demoActivityPresenter);

    }
}
