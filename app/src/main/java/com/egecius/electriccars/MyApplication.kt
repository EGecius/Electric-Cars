@file:Suppress("unused")

package com.egecius.electriccars

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()

//        printMockWebServerUrl()
    }

    @SuppressLint("CheckResult")
    private fun printMockWebServerUrl() {
        getMockServerUrl()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( Consumer {
                Log.v("Eg:MyApplication:27", "printMockWebServerUrl url: $it")
            })
    }

    fun getMockServerUrl(): Single<String> = mockWebSeverInitializer.getUrl()

}