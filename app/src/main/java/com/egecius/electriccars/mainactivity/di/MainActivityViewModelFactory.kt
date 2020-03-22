package com.egecius.electriccars.mainactivity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egecius.electriccars.mainactivity.MainActivityViewModel
import com.egecius.electriccars.repository.CarsRepository

class MainActivityViewModelFactory(private val carsRepository: CarsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") // taken from Google sample
        return MainActivityViewModel(carsRepository) as T
    }
}
