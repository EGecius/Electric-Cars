package com.egecius.electriccars.mainactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car

class MainActivityViewModel(private val carsRepository: CarsRepository) : ViewModel() {

    val isUpdating = MutableLiveData(false)

    val coroutineLiveData = liveData {
        isUpdating.value = true
        setIsUpdating(isUpdating)

        val cars = carsRepository.getCars()
        emit(cars)
        isUpdating.value = false
    }

    private fun setIsUpdating(updating: MutableLiveData<Boolean>) {
    	Log.v("Eg:MainActivityViewModel:24", "setIsUpdating()")

        updating.value = true
    }

    fun retryFetching() {
        // TODO: 22/03/2020
    }
}