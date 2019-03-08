package com.egecius.electriccars.mainactivity.di

import androidx.lifecycle.ViewModelProviders
import com.egecius.electriccars.mainactivity.MainActivity
import com.egecius.electriccars.mainactivity.MainActivityViewModel
import com.egecius.electriccars.repository.CarsLiveData
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.CarsDatabase
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    fun provideMainActivityPresenter(carsLiveData: CarsLiveData): MainActivityViewModel {
        val presenter = ViewModelProviders.of(mainActivity).get(MainActivityViewModel::class.java)
        presenter.init(carsLiveData)
        return presenter
    }

    @Provides
    fun provideCarsLiveData(): CarsLiveData {
        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val carDao = CarsDatabase.getInstance(mainActivity).carDao()
        val carsRepository = CarsRepository(carsRetrofitService, carDao)
        return CarsLiveData(carsRepository)
    }

}
