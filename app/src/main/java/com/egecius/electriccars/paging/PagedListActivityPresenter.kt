package com.egecius.electriccars.paging

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.egecius.electriccars.room.Car

class PagedListActivityPresenter : ViewModel() {

    private lateinit var carsLiveData: LiveData<PagedList<Car>>

    fun init(carsLiveData: LiveData<PagedList<Car>>) {
        this.carsLiveData = carsLiveData
    }

    fun startPresenting(
        lifecycleOwner: LifecycleOwner,
        view: PagedListActivityView
    ) {
        carsLiveData.observe(lifecycleOwner, Observer { cars ->
            view.showCars(cars)
        })
    }

}
