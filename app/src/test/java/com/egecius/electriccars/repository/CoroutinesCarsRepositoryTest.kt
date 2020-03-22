package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class CoroutinesCarsRepositoryTest {

    private lateinit var sut: CarsRepository

    @Mock
    lateinit var carRetrofitService : CarRetrofitService
    @Mock
    lateinit var carDao: CarDao

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val carList = listOf(Car("name", "img"))

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        sut = CarsRepository(carRetrofitService, carDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `returns cars from network`() = runBlockingTest {
        givenCarServiceWillReturn()

        val cars = sut.getCars()

        assertThat(cars).isEqualTo(carList)
    }

    private suspend fun givenCarServiceWillReturn() {
        given(carRetrofitService.getCarsFull()).willReturn(carList)
    }
}