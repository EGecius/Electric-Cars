package com.egecius.electriccars.mainactivity.di;

import com.egecius.electriccars.mainactivity.MainActivityPresenter;
import dagger.Provides;

public class MainActivityModule {

    @Provides
    public MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }
}
