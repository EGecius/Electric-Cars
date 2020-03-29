package com.egecius.electriccars.mainactivity

import androidx.lifecycle.*
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import kotlinx.coroutines.launch

class MainActivityViewModel(private val carsRepository: CarsRepository) : ViewModel() {

    val isUpdating = MutableLiveData(false)
    val isError = MutableLiveData(false)

    val coroutineLiveData = MutableLiveData<List<Car>>()

    init {
        showCards()
    }

    private fun showCards() {
        viewModelScope.launch {
            isUpdating.value = true
            val cars: List<Car> = carsRepository.getCars()
            coroutineLiveData.value = cars
            isUpdating.value = false
        }.invokeOnCompletion {
            it?.let {
                isError.value = true
            }
            isUpdating.value = false
        }
    }

    fun retryFetching() {
        showCards()
    }
}