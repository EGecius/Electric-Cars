package com.egecius.electriccars.retrofit

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .client(createLoggingOkHttpClient())
            .build()
            .create(CarsRetrofitService::class.java)
    }

    private fun createLoggingOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}

interface CarsRetrofitService {

    @GET(ENDPOINT_CARS_FULL)
    fun getCarsFull(): Single<List<Car>>

    @GET(ENDPOINT_CARS_0)
    fun getCars0(): Single<List<Car>>

    @GET(ENDPOINT_CARS_1)
    fun getCars1(): Single<List<Car>>

    @GET(ENDPOINT_CARS_2)
    fun getCars2(): Single<List<Car>>

    companion object {
        const val ENDPOINT_CARS_FULL = "electric_cars.json"
        const val ENDPOINT_CARS_0 = "electric_cars_0.json"
        const val ENDPOINT_CARS_1 = "electric_cars_1.json"
        const val ENDPOINT_CARS_2 = "electric_cars_2.json"
    }

}