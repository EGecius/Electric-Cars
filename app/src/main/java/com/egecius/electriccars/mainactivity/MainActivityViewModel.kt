package com.egecius.electriccars.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import kotlinx.coroutines.launch

class MainActivityViewModel(private val carsRepository: CarsRepository) : ViewModel() {

    val isUpdating = MutableLiveData(false)
    val isError = MutableLiveData(false)

    val carsList = MutableLiveData<List<Car>>()

    init {
        fetchCars()
    }

    private fun fetchCars() {
        viewModelScope.launch {
            isUpdating.value = true
            carsList.value = carsRepository.getCars()
            isUpdating.value = false
        }.invokeOnCompletion {
            it?.let {
                isError.value = true
            }
            isUpdating.value = false
        }
    }

    fun retryFetching() {
        fetchCars()
    }
}