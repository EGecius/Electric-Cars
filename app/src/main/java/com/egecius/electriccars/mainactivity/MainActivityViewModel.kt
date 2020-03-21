package com.egecius.electriccars.mainactivity

import androidx.lifecycle.ViewModel
import com.egecius.electriccars.app.Schedulers
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private lateinit var view: MainActivityView
    private lateinit var carsRepository: CarsRepository
    private lateinit var schedulers: Schedulers
    private val compositeDisposable = CompositeDisposable()

    fun init(
        carsRepository: CarsRepository,
        schedulers: Schedulers
    ) {
        this.carsRepository = carsRepository
        this.schedulers = schedulers
    }

    fun startPresenting(view: MainActivityView) {
        this.view = view
        showCars()
    }

    private fun showCars() {
        CoroutineScope(IO).launch {
            val cars: List<Car> = carsRepository.getCars()
            withContext(Main) {
                view.showCars(cars)
            }
        }.invokeOnCompletion {
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
        compositeDisposable.clear()
    }

}