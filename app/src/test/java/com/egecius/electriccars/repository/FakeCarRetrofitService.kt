package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car
import retrofit2.Call

class FakeCarRetrofitService : CarRetrofitService {

    val carsList = listOf(Car("Tesla 3", "img"))

    override fun getCarsByPages(page: Int): Call<List<Car>> {
        TODO("not implemented")
    }

    override suspend fun getCarsFull(): List<Car> {
        return carsList
    }

}