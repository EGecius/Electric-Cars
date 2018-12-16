package com.egecius.electriccars.retrofit

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RetrofitAdapter {

    private val baseUrl = MockWebSeverInitializer.BASE_URL

    fun setupRetrofit(): CarsRetrofitService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CarsRetrofitService::class.java)
    }
}

interface CarsRetrofitService {

    @GET(ENDPOINT_CARS)
    fun cars(): Single<List<Car>>

    companion object {
        const val ENDPOINT_CARS = "/electric_cars"
    }

}