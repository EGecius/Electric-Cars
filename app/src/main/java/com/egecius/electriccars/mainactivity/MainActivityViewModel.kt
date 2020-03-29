package com.egecius.electriccars.mainactivity

import androidx.lifecycle.*
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import kotlinx.coroutines.launch

class MainActivityViewModel(private val carsRepository: CarsRepository) : ViewModel() {

    val isUpdating = MutableLiveData(false)

    val coroutineLiveData = MutableLiveData<List<Car>>()

    init {
        viewModelScope.launch {
            isUpdating.value = true
            val cars: List<Car> = carsRepository.getCars()
            coroutineLiveData.value = cars
            isUpdating.value = false
        }
    }

    fun retryFetching() {
        // TODO: 22/03/2020
    }
}