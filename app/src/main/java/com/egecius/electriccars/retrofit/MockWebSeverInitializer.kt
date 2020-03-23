package com.egecius.electriccars.retrofit

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

                Thread.sleep(NETWORK_DELAY_MILLIS)

                if (request?.path.equals("/" + CarRetrofitService.ENDPOINT_CARS_FULL)) {
                    return MockResponse().setBody(getElectricCars())
                }
                return MockResponse().setResponseCode(404)
            }
        }

        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(dispatcher)

        Completable.fromCallable { mockWebServer.start(PORT) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    private fun getElectricCars(): String {
        val inputStream = javaClass.getResourceAsStream("/" + "electric_cars.json")!!
        return Scanner(inputStream).useDelimiter("\\A").next()
    }

    companion object {
        private const val NETWORK_DELAY_MILLIS = 300L
        const val PORT = 54034
        const val BASE_URL: String = "http://localhost:$PORT"
    }

}
