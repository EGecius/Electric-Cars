package com.egecius.electriccars

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.egecius.electriccars.room.CarsDatabase

class MainActivityPresenter : ViewModel() {

    private lateinit var view : MainActivityView
    private lateinit var carsLiveData : CarsLiveData

    fun startPresenting(
        view: MainActivityView,
        lifecycleOwner: LifecycleOwner,
        mockSeverUrl: String,
        context: Context
    ) {
        this.view = view
        val carsDatabase = CarsDatabase.getInstance(context)
        carsLiveData = CarsLiveData(CarsRepository(RetrofitAdapter(mockSeverUrl), carsDatabase))

        showCars(lifecycleOwner)
    }

    private fun showCars(lifecycleOwner: LifecycleOwner) {
        carsLiveData.observe(lifecycleOwner, Observer {
            view.showCars(it)
        })
    }

}