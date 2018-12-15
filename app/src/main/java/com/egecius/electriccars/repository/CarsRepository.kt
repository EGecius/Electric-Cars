package com.egecius.electriccars.repository

import android.util.Log
import com.egecius.electriccars.retrofit.CarsRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao
import io.reactivex.Completable
import io.reactivex.Single

class CarsRepository(
    private val carsRetrofitService: CarsRetrofitService,
    private val carDao: CarDao
) {

    fun getCars(): Single<List<Car>> {
        return carsRetrofitService.cars()
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