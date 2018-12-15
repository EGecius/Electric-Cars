package com.egecius.electriccars

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

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
