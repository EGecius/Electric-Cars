package com.egecius.electriccars

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RetrofitAdapter(private val mockServerUrl: String) {

    fun setupRetrofit(): CarsRetrofitService {

        return Retrofit.Builder()
            .baseUrl(mockServerUrl)
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