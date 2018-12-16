package com.egecius.electriccars.mainactivity.di

import androidx.lifecycle.ViewModelProviders
import com.egecius.electriccars.mainactivity.MainActivity
import com.egecius.electriccars.mainactivity.MainActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    fun provideMainActivityPresenter(): MainActivityPresenter {
        return ViewModelProviders.of(mainActivity).get(MainActivityPresenter::class.java)
    }
}
