package com.egecius.electriccars.mainactivity.di

import androidx.lifecycle.ViewModelProviders
import com.egecius.electriccars.app.AndroidSchedulers
import com.egecius.electriccars.app.Schedulers
import com.egecius.electriccars.mainactivity.MainActivity
import com.egecius.electriccars.mainactivity.MainActivityViewModel
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.CarsDatabase
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity) {

    @Provides
    fun provideMainActivityPresenter(
        carsRepository: CarsRepository,
        schedulers: Schedulers
    ): MainActivityViewModel {
        val presenter = ViewModelProviders.of(mainActivity).get(MainActivityViewModel::class.java)
        presenter.init(carsRepository, schedulers)
        return presenter
    }

    @Provides
    fun provideCarsLiveData(): CarsRepository {
        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val carDao = CarsDatabase.getInstance(mainActivity).carDao()
        return CarsRepository(carsRetrofitService, carDao)
    }

    @Provides
    fun provideSchedulers(): Schedulers {
        return AndroidSchedulers()
    }

}
