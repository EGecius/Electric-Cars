package com.egecius.electriccars

import android.util.Log
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarsDatabase
import io.reactivex.Single

class CarsRepository(retrofitAdapter: RetrofitAdapter, private val carsDatabase: CarsDatabase) {

    private val retrofitService = retrofitAdapter.setupRetrofit()

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
            .doOnSuccess {
                storeCarsInDatabase(it)
            }
    }

    private fun storeCarsInDatabase(cars: List<Car>) {

        val carDao = carsDatabase.carDao()
        for (car in cars) {
            Log.v("Eg:CarsRepository:27", "storeCarsInDatabase inserting: ${car.name}")
            carDao.insertCar(car)
        }

        val allCars = carDao.allCars
        Log.i("Eg:CarsRepository:29", "storeCarsInDatabase allCars.size " + allCars.size)
        Log.i("Eg:CarsRepository:38", "storeCarsInDatabase allCars: $allCars")
    }

}