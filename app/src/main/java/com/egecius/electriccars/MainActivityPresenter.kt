package com.egecius.electriccars

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityPresenter : ViewModel() {

    fun startPresenting(view: MainActivityView, mockSeverUrl: String) {
        val cars = CarsFileReader().cars
        view.showCars(cars)

        testRepository(mockSeverUrl)
    }

    @SuppressLint("LongLogTag", "CheckResult")
    private fun testRepository(mockSeverUrl: String) {

        CarsRepository(mockSeverUrl).getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cars, throwable ->
                Log.i("Eg:MainActivityPresenter:27", "testRepository cars $cars")
                Log.e("Eg:MainActivityPresenter:28", "testRepository throwable $throwable")
            }
    }

}