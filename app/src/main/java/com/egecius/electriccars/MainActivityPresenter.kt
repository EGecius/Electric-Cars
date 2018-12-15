package com.egecius.electriccars

import androidx.lifecycle.ViewModel

class MainActivityPresenter : ViewModel() {

    fun startPresenting(view: MainActivityView) {
        val cars = CarsFileReader().cars
        view.showCars(cars)
    }

}