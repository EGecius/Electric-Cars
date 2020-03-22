package com.egecius.electriccars.mainactivity

import androidx.lifecycle.*
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car

class MainActivityViewModel : ViewModel() {

    private lateinit var view: MainActivityView
    private lateinit var carsRepository: CarsRepository

    fun init(carsRepository: CarsRepository) {
        this.carsRepository = carsRepository
    }

    fun startPresenting(
        view: MainActivityView,
        lifecycleOwner: LifecycleOwner
    ) {
        this.view = view
        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        val liveData: LiveData<List<Car>> = liveData {
            val cars = carsRepository.getCars()
            emit(cars)
        }
        liveData.observe(lifecycleOwner, Observer {
            view.showCars(it)
        })

        // TODO: 22/03/2020 implement error handling
    }

    fun retryFetching(lifecycleOwner: LifecycleOwner) {
        view.showLoadingInProgress()
        showCars(lifecycleOwner)
    }
}