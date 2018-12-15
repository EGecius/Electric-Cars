@file:Suppress("unused")

package com.egecius.electriccars

import android.app.Application
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.util.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupMockWebSever()
    }

    private fun setupMockWebSever() {

        val mockWebServer = MockWebServer()

        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                if (request?.path.equals("electric_cars")) {
                    return MockResponse().setBody(getElectricCars())
                }
                return MockResponse().setResponseCode(404)
            }
        }

        mockWebServer.setDispatcher(dispatcher)
    }

    private fun getElectricCars(): String {
        val inputStream = javaClass.getResourceAsStream("/electric_cars")
        return Scanner(inputStream).useDelimiter("\\A").next()
    }

}