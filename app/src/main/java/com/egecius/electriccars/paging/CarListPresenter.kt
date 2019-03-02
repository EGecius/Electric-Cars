package com.egecius.electriccars.paging

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.egecius.electriccars.room.Car

class CarListPresenter : ViewModel() {

    private lateinit var view: View
    private lateinit var carsLiveData: LiveData<PagedList<Car>>

    fun init(carsLiveData: LiveData<PagedList<Car>>) {
        this.carsLiveData = carsLiveData
    }

    fun startPresenting(
        view: View,
        lifecycleOwner: LifecycleOwner
    ) {
        this.view = view

        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        carsLiveData.observe(lifecycleOwner, Observer { cars ->
            view.showCars(cars)
        })
    }

    interface View {

        fun showCars(cars: PagedList<Car>)

    }

}
