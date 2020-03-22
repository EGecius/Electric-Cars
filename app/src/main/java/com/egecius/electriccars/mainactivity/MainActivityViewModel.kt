package com.egecius.electriccars.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car

class MainActivityViewModel : ViewModel() {

    private lateinit var carsRepository: CarsRepository

    val coroutineLiveData: LiveData<List<Car>> = liveData {
        val cars = carsRepository.getCars()
        emit(cars)
    }

    fun init(carsRepository: CarsRepository) {
        this.carsRepository = carsRepository
    }

    fun retryFetching() {
        // TODO: 22/03/2020
    }
}