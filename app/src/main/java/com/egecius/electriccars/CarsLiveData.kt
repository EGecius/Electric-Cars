package com.egecius.electriccars

import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CarsLiveData(private val carsRepository: CarsRepository) : LiveData<List<Car>>() {

    var disposable: Disposable? = null

    override fun onActive() {
        disposable = carsRepository.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cars ->
                value = cars
            }
    }

    override fun onInactive() {
        disposable?.dispose()
    }
}