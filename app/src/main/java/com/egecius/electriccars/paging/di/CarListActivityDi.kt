package com.egecius.electriccars.paging.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egecius.electriccars.paging.CarDataSourceFactory
import com.egecius.electriccars.paging.PagedListCarActivity
import com.egecius.electriccars.paging.CarListViewModel
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [CarListModule::class])
interface CarListActivityComponent {

    fun injectInto(pagedListCarActivity: PagedListCarActivity)
}

@Module
class CarListModule(private val carActivity: PagedListCarActivity) {

    @Provides
    fun providesPresenter(carsLiveData: LiveData<PagedList<Car>>): CarListViewModel {
        val presenter = ViewModelProviders.of(carActivity).get(CarListViewModel::class.java)
        presenter.init(carsLiveData)
        return presenter
    }

    @Provides
    fun providesCarsLiveData(): LiveData<PagedList<Car>> {

        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val myDataSourceFactory = CarDataSourceFactory(carsRetrofitService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(myDataSourceFactory, config).build()
    }
}
