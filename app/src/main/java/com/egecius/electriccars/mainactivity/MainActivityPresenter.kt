package com.egecius.electriccars.mainactivity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.egecius.electriccars.repository.CarsLiveData

class MainActivityPresenter : ViewModel() {

    private lateinit var view : MainActivityView
    private lateinit var carsLiveData : CarsLiveData

    fun init(carsLiveData: CarsLiveData) {
        this.carsLiveData = carsLiveData
    }

    fun startPresenting(
        view: MainActivityView,
        lifecycleOwner: LifecycleOwner
    ) {
        this.view = view

        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        carsLiveData.observe(lifecycleOwner, Observer {

            val data = it.data
            if (data != null) {
                view.showCars(data)
            } else {
                view.showLoadingError()
            }
        })
    }

    fun retryFetching(lifecycleOwner: LifecycleOwner) {
        showCars(lifecycleOwner)
    }

}