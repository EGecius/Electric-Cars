package com.egecius.electriccars.paging

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.egecius.electriccars.retrofit.CarsRetrofitService
import com.egecius.electriccars.room.Car

class MyDataSource(private val carsRetrofitService: CarsRetrofitService) : PageKeyedDataSource<Long, Car>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Car>) {
        val cars = carsRetrofitService.getCarsByPages(0).execute().body()
        cars?.let { callback.onResult(it, 0, 1) }
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Car>) {
    	// append your list

        val page = params.key.toInt()
        val result = carsRetrofitService.getCarsByPages(page).execute().body()
        result?.let { callback.onResult(it, params.key + 1) }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Car>) {
        // prepend your list - not implemented
    }

}
