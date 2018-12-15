package com.egecius.electriccars

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivityPresenter : ViewModel() {

    private lateinit var view : MainActivityView
    private lateinit var carsLiveData : CarsLiveData

    fun startPresenting(
        view: MainActivityView,
        lifecycleOwner: LifecycleOwner,
        mockSeverUrl: String) {

        this.view = view
        carsLiveData = CarsLiveData(CarsRepository(RetrofitAdapter(mockSeverUrl)))

        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        carsLiveData.observe(lifecycleOwner, Observer {
            view.showCars(it)
        })
    }

}