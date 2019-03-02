package com.egecius.electriccars.paging

import androidx.paging.DataSource
import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car


class CarDataSourceFactory(private val carRetrofitService: CarRetrofitService) : DataSource.Factory<Long, Car>() {

    override fun create(): DataSource<Long, Car> {
        return CarDataSource(carRetrofitService)
    }

}
