package com.egecius.electriccars.repository

import androidx.lifecycle.LiveData
import com.egecius.electriccars.room.Car
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class CarsLiveData(private val carsRepository: CarsRepository) : LiveData<Result<List<Car>>>() {

    private var disposable: Disposable? = null

    override fun onActive() {
        extractValueFromRepository()
    }

    private fun extractValueFromRepository() {
        disposable = carsRepository.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cars, throwable ->
                value = Result(cars, throwable)
            }
    }

    override fun onInactive() {
        disposable?.dispose()
    }

    open fun retry() {
        extractValueFromRepository()
    }
}