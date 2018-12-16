package com.egecius.electriccars.mainactivity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.egecius.electriccars.repository.CarsLiveData
import com.egecius.electriccars.repository.CarsRepository

class MainActivityPresenter : ViewModel() {

    private lateinit var view : MainActivityView
    private lateinit var carsLiveData : CarsLiveData
    private lateinit var carsRepository: CarsRepository

    fun init(carsRepository: CarsRepository) {
        this.carsRepository = carsRepository
    }

    fun startPresenting(
        view: MainActivityView,
        lifecycleOwner: LifecycleOwner
    ) {
        this.view = view
        carsLiveData = CarsLiveData(carsRepository)

        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        carsLiveData.observe(lifecycleOwner, Observer {
            view.showCars(it)
        })
    }

}