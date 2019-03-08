package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao
import io.reactivex.Single

open class CarsRepository(
    private val carRetrofitService: CarRetrofitService,
    private val carDao: CarDao
) {

    open fun getCars(): Single<List<Car>> {
        return carRetrofitService.getCarsFull()
            .doOnSuccess { cars -> storeCarsInDatabase(cars) }
            .flatMap { returnInternetOrDbData(it) }
    }

    private fun returnInternetOrDbData(dataInternet: List<Car>): Single<List<Car>> {

        return if (dataInternet.isEmpty()) {
            Single.just(carDao.loadAllCars())
        } else {
            Single.just(dataInternet)
        }
    }

    private fun storeCarsInDatabase(cars: List<Car>) {
        for (car in cars) {
            carDao.insertCar(car)
        }
    }

}