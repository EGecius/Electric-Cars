@file:Suppress("unused")

package com.egecius.electriccars

import android.app.Application

class MyApplication : Application() {

    private val mockWebSeverInitializer = MockWebSeverInitializer()

    override fun onCreate() {
        super.onCreate()
        mockWebSeverInitializer.init()
    }

}