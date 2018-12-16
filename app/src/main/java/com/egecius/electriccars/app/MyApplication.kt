@file:Suppress("unused")

package com.egecius.electriccars.app

import android.app.Application
import com.egecius.electriccars.retrofit.MockWebSeverInitializer

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()
    }

}