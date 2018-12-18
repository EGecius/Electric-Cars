package com.egecius.electriccars.paging

import androidx.paging.DataSource
import com.egecius.electriccars.retrofit.CarsRetrofitService
import com.egecius.electriccars.room.Car


class MyDataSourceFactory(private val carsRetrofitService: CarsRetrofitService) : DataSource.Factory<Long, Car>() {

    override fun create(): DataSource<Long, Car> {
        return MyDataSource(carsRetrofitService)
    }

}
