package com.egecius.electriccars.mainactivity

import android.util.Log
import androidx.lifecycle.*
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        startInfiniteLoop()
    }

    private fun startInfiniteLoop() {
        viewModelScope.launch {
            val start = System.currentTimeMillis()
            while (true) {
                delay(1000)
                val diff = System.currentTimeMillis() - start
                // see how this gets cancelled when the app goes into the background
                Log.v("Eg:MainActivityViewModel:33", "startInfiniteLoop() diff: $diff")
            }
        }
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