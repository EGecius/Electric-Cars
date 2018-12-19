package com.egecius.electriccars.paging

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car

class PagedListActivityPresenter : ViewModel() {

    fun startPresenting(
        lifecycleOwner: LifecycleOwner,
        view: PagedListActivityView
    ) {
        getCarsLiveData().observe(lifecycleOwner, Observer { cars ->
            view.showCars(cars)
        })
    }

    private fun getCarsLiveData(): LiveData<PagedList<Car>> {

        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val myDataSourceFactory = MyDataSourceFactory(carsRetrofitService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5).build()

        return LivePagedListBuilder(myDataSourceFactory, config).build()
    }

}
