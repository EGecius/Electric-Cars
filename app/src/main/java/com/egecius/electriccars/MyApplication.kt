@file:Suppress("unused")

package com.egecius.electriccars

import android.app.Application
import io.reactivex.Single

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()

    }

    fun getMockServerHostName(): Single<String> = mockWebSeverInitializer.getHostName()

}