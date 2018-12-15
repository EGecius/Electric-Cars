package com.egecius.electriccars

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.util.*

class MockWebSeverInitializer {

    private lateinit var mockWebServer : MockWebServer

    fun init() {
        setupMockWebSever()
    }

    private fun setupMockWebSever() {
        val dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                if (request?.path.equals("electric_cars")) {
                    return MockResponse().setBody(getElectricCars())
                }
                return MockResponse().setResponseCode(404)
            }
        }

        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(dispatcher)
    }

    private fun getElectricCars(): String {
        val inputStream = javaClass.getResourceAsStream("/electric_cars")
        return Scanner(inputStream).useDelimiter("\\A").next()
    }

}
