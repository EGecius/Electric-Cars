package com.egecius.electriccars

import android.util.Log
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarRoom
import com.egecius.electriccars.room.CarsDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarsRepository(retrofitAdapter: RetrofitAdapter, private val carsDatabase: CarsDatabase) {

    private val retrofitService = retrofitAdapter.setupRetrofit()

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
            .doOnSuccess {
                storeCarsInDatabase(it)
            }
    }

    private fun storeCarsInDatabase(cars: List<Car>) {
    	Log.d("Eg:CarsRepository:23", "storeCarsInDatabase cars $cars")

        val carDao = carsDatabase.carDao()
        for (car in cars) {
            carDao.insertCar(CarRoom(car))
        }

        val disposable = carDao.car
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                Log.i("Eg:CarsRepository:26", "storeCarsInDatabase car: $it")
            }


    }

}