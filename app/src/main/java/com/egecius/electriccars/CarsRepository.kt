package com.egecius.electriccars

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class CarsRepository {

    private val retrofitService = setupRetrofit()

    fun getCars(): Single<List<Car>> {
        return retrofitService.cars()
    }

    private fun setupRetrofit(): CarsRetrofitService {

        // TODO: 15/12/2018 pass correct base url

        return Retrofit.Builder()
            .baseUrl("localhost")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CarsRetrofitService::class.java)
    }

}

interface CarsRetrofitService {

    @GET("/electric_cars")
    fun cars(): Single<List<Car>>

}