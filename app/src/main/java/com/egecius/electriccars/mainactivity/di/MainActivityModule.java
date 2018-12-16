package com.egecius.electriccars.mainactivity.di;

import androidx.lifecycle.ViewModelProviders;
import com.egecius.electriccars.mainactivity.MainActivity;
import com.egecius.electriccars.mainactivity.MainActivityPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    public MainActivityPresenter provideMainActivityPresenter() {
        return ViewModelProviders.of(mainActivity).get(MainActivityPresenter.class);
    }
}
