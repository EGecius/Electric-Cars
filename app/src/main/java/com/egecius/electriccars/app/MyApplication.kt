@file:Suppress("unused")

package com.egecius.electriccars.app

import android.app.Application
import com.egecius.electriccars.retrofit.MockWebServerInitializer

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebServerInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()
    }

}
