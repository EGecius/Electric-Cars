package com.egecius.electriccars.retrofit

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer

class MockWebServerInitializer {

    private lateinit var mockWebServer: MockWebServer

    fun init() {
        setupMockWebSever()
    }

    private fun setupMockWebSever() {
        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(MockWebServerDispatcher())

        Completable.fromCallable { mockWebServer.start(PORT) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    companion object {
        const val PORT = 54034
        const val BASE_URL: String = "http://localhost:$PORT"
    }
}
