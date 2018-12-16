package com.egecius.electriccars.mainactivity.di;

import com.egecius.electriccars.mainactivity.MainActivity;
import dagger.Component;

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void injectInto(MainActivity mainActivity);
}
