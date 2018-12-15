package com.egecius.electriccars

import retrofit2.Retrofit
import retrofit2.http.GET

class CarsRepository {

    fun setupRetrofit() : CarsRetrofitService {

        // TODO: 15/12/2018 pass correct base url

        return Retrofit.Builder()
            .baseUrl("localhost")
            .build()
            .create(CarsRetrofitService::class.java)
    }

}

interface CarsRetrofitService {

    @GET("/electric_cars")
    fun cars(): List<Car>

}