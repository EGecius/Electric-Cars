package com.egecius.electriccars.demo

import dagger.Module
import dagger.Provides

@Module
class DemoActivityModule {

    @Provides
    fun provideDemoActivityPresenter(): DemoActivityPresenter {
        return DemoActivityPresenter()
    }
}
