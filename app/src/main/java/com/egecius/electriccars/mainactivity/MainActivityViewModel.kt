package com.egecius.electriccars.mainactivity

import androidx.lifecycle.ViewModel
import com.egecius.electriccars.app.Schedulers
import com.egecius.electriccars.repository.CarsRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel : ViewModel() {

    private lateinit var view : MainActivityView
    private lateinit var carsRepository : CarsRepository
    private lateinit var schedulers: Schedulers
    private val compositeDisposable = CompositeDisposable()

    fun init(
        carsRepository: CarsRepository,
        schedulers: Schedulers
    ) {
        this.carsRepository = carsRepository
        this.schedulers = schedulers
    }

    fun startPresenting(
        view: MainActivityView) {
        this.view = view
        showCars()
    }

    private fun showCars() {
        val disposable = carsRepository.getCars()
            .subscribeOn(schedulers.getExecutionScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showCars(it)
            }, {
                view.showLoadingError()
            })

        compositeDisposable.add(disposable)
    }

    fun retryFetching() {
        view.showLoadingInProgress()
        showCars()
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

}