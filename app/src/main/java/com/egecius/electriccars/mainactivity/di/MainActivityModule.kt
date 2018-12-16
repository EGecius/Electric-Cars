package com.egecius.electriccars.mainactivity.di

import androidx.lifecycle.ViewModelProviders
import com.egecius.electriccars.mainactivity.MainActivity
import com.egecius.electriccars.mainactivity.MainActivityPresenter
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.CarsDatabase
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    fun provideMainActivityPresenter(): MainActivityPresenter {
        val presenter = ViewModelProviders.of(mainActivity).get(MainActivityPresenter::class.java)

        val carsDatabase = CarsDatabase.getInstance(mainActivity)
        val carsRepository = CarsRepository(
            RetrofitAdapter().setupRetrofit(),
            carsDatabase.carDao()
        )

        presenter.init(carsRepository)

        return presenter
    }
}
