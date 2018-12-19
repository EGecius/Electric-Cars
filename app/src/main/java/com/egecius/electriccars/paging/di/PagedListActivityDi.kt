package com.egecius.electriccars.paging.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egecius.electriccars.paging.MyDataSourceFactory
import com.egecius.electriccars.paging.PagedListActivity
import com.egecius.electriccars.paging.PagedListActivityPresenter
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [PagedListActivityModule::class])
interface PagedListActivityComponent {

    fun injectInto(pagedListActivity: PagedListActivity)
}

@Module
class PagedListActivityModule(private val activity: PagedListActivity) {

    @Provides
    fun providesPresenter(carsLiveData: LiveData<PagedList<Car>>): PagedListActivityPresenter {
        val presenter = ViewModelProviders.of(activity).get(PagedListActivityPresenter::class.java)
        presenter.init(carsLiveData)
        return presenter
    }

    @Provides
    fun providesCarsLiveData(): LiveData<PagedList<Car>> {

        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val myDataSourceFactory = MyDataSourceFactory(carsRetrofitService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()

        return LivePagedListBuilder(myDataSourceFactory, config).build()
    }
}
