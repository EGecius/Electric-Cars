package com.egecius.electriccars.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.egecius.electriccars.retrofit.CarsRetrofitService
import com.egecius.electriccars.room.Car
import io.reactivex.Single

class MyDataSource(private val carsRetrofitService: CarsRetrofitService) : PageKeyedDataSource<Long, Car>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Car>) {
        carsRetrofitService.getCars0().subscribe({ cars ->
            callback.onResult(cars, 0, 1)

            Log.i("Eg:MyDataSource:15", "loadInitial cars $cars")
        },
            { throwable ->
                Log.w("Eg:MyDataSource:17", "loadInitial throwable $throwable")
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Car>) {

        val key = params.key.toInt()
        val carsSingle: Single<List<Car>> = if (key == 1) {
            carsRetrofitService.getCars1()
        } else if (key == 2) {
            carsRetrofitService.getCars2()
        } else {
            Single.just(emptyList())
        }

        carsSingle.subscribe({ cars ->
            callback.onResult(cars, params.key + 1)

            Log.d("Eg:MyDataSource:15", "loadAfter cars $cars")
        },
            { throwable ->
                Log.w("Eg:MyDataSource:17", "loadInitial throwable $throwable")
            })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Car>) {
        Log.v("Eg:MyDataSource:28", "loadBefore")
    }

}
