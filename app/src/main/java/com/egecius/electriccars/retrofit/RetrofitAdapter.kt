package com.egecius.electriccars.retrofit

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RetrofitAdapter {

    private val mockWebServerBaseUrl = MockWebSeverInitializer.BASE_URL
    private val onlineBaseUrl = "https://raw.githubusercontent.com/EGecius/json/master/"

    fun setupRetrofit(): CarsRetrofitService {
        return Retrofit.Builder()
            .baseUrl(onlineBaseUrl)
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
        const val ENDPOINT_CARS = "electric_cars.json"
    }

}