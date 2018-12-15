package com.egecius.electriccars.repository

import android.util.Log
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarsDatabase
import io.reactivex.Completable
import io.reactivex.Single

class CarsRepository(retrofitAdapter: RetrofitAdapter, carsDatabase: CarsDatabase) {

    private val retrofitService = retrofitAdapter.setupRetrofit()
    private val carDao = carsDatabase.carDao()!!

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
            .flatMapCompletable {
                Completable.fromAction {
                    storeCarsInDatabase(it)
                }
            }.toSingle {
                carDao.loadAllCars()
            }
    }

    private fun storeCarsInDatabase(cars: List<Car>) {
        for (car in cars) {
            Log.v("Eg:CarsRepository:27", "storeCarsInDatabase inserting: ${car.name}")
            carDao.insertCar(car)
        }
    }

}