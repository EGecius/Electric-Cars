package com.egecius.electriccars

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import retrofit2.http.GET

class CarsRepository(retrofitAdapter: RetrofitAdapter) {

    private val retrofitService = retrofitAdapter.setupRetrofit()

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
    }

}

interface CarsRetrofitService {

    @GET("/electric_cars")
    fun cars(): Single<List<Car>>

}