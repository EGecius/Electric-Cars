package com.egecius.electriccars

import com.egecius.electriccars.room.Car
import io.reactivex.Single

class CarsRepository(retrofitAdapter: RetrofitAdapter) {

    private val retrofitService = retrofitAdapter.setupRetrofit()

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
    }

}