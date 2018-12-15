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
    	Log.v("Eg:CarsRepository:22", "storeCarsInDatabase")

//        for (car in cars) {
//            carsDatabase.carDao().insertCar(car)
//        }
//
//        val disposable = carsDatabase.carDao().car
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { it ->
//                Log.i("Eg:CarsRepository:26", "storeCarsInDatabase car: $it")
//            }
    }

}