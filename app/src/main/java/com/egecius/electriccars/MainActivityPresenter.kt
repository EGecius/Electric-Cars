package com.egecius.electriccars

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityPresenter : ViewModel() {

    private lateinit var view : MainActivityView

    fun startPresenting(view: MainActivityView, mockSeverUrl: String) {
        this.view = view

        showCars(mockSeverUrl)
    }

    @SuppressLint("LongLogTag", "CheckResult")
    private fun showCars(mockSeverUrl: String) {

        CarsRepository(mockSeverUrl).getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cars ->
                view.showCars(cars)
            }
    }

}