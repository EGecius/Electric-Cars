package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao

open class CarsRepository(
    private val carRetrofitService: CarRetrofitService,
    private val carDao: CarDao
) {

    open suspend fun getCars(): List<Car> {
        val carsFull = carRetrofitService.getCarsFull()
        storeCarsInDatabase(carsFull)
        return returnInternetOrDbData(carsFull)
    }

    private suspend fun returnInternetOrDbData(dataInternet: List<Car>): List<Car> {
        return if (dataInternet.isEmpty()) {
            carDao.loadAllCars()
        } else {
            dataInternet
        }
    }

    private suspend fun storeCarsInDatabase(cars: List<Car>) {
        for (car in cars) {
            carDao.insertCar(car)
        }
    }
}