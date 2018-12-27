package com.egecius.electriccars.retrofit

import com.egecius.electriccars.room.Car
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitAdapter {

    private val mockWebServerBaseUrl = MockWebSeverInitializer.BASE_URL
    private val baseUrlHeroku = "https://mighty-spire-24044.herokuapp.com/"

    fun setupRetrofit(): CarsRetrofitService {
        return Retrofit.Builder()
            .baseUrl(baseUrlHeroku)
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

    // only works with Heroku base url
    @GET("electric")
    fun getCarsByPages(@Query("page") page: Int): Call<List<Car>>

    // TODO: 27/12/2018 move  getCarsFull() to a separate interface to avoid crashes

    @GET(ENDPOINT_CARS_FULL)
    fun getCarsFull(): Single<List<Car>>

    companion object {
        const val ENDPOINT_CARS_FULL = "electric_full"
    }

}