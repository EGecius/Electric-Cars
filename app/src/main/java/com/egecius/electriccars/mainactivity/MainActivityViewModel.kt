package com.egecius.electriccars.mainactivity

import androidx.lifecycle.ViewModel
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private lateinit var view: MainActivityView
    private lateinit var carsRepository: CarsRepository
    private lateinit var job: Job

    fun init(carsRepository: CarsRepository) {
        this.carsRepository = carsRepository
    }

    fun startPresenting(view: MainActivityView) {
        this.view = view
        showCars()
    }

    private fun showCars() {
        val job: Job = CoroutineScope(Main).launch {
            val cars: List<Car> = carsRepository.getCars()
            view.showCars(cars)
        }
        job.invokeOnCompletion {
            it?.let {
                view.showLoadingError()
            }
        }
    }

    fun retryFetching() {
        view.showLoadingInProgress()
        showCars()
    }

    fun stopPresenting() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}