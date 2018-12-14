package com.egecius.electriccars

import androidx.lifecycle.ViewModel

class MainActivityPresenter : ViewModel() {

    fun startPresenting(view: MainActivityView) {
        val cars = CarsRepository().carsList
        view.showCars(cars)
    }

}