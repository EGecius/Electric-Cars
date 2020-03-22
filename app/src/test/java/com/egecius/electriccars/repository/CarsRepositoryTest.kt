package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class CarsRepositoryTest {

    private lateinit var sut: CarsRepository

    @Mock
    lateinit var carRetrofitService: CarRetrofitService
    @Mock
    lateinit var carDao: CarDao

    private val carInternet = Car("name internet", "img internet")
    private val dataInternet: List<Car> = listOf(carInternet)


    private val carDb = Car("name db", "img db")
    private val dataDb: List<Car> = listOf(carDb)

    @Before
    fun setUp() {
        sut = CarsRepository(carRetrofitService, carDao)
    }

    @Test
    fun `returns data from internet when db data available`() = runBlockingTest {
        givenInternetDataAvailable()
        givenDbDataAvailable()

        val result = sut.getCars()

        assertThat(result).isEqualTo(dataInternet)
    }

    @Test
    fun `returns data from internet when db data missing`() = runBlockingTest {
        givenInternetDataAvailable()
        givenDbDataEmpty()

        val result = sut.getCars()

        assertThat(result).isEqualTo(dataInternet)
    }

    private suspend fun givenDbDataEmpty() {
        given(carDao.loadAllCars()).willReturn(emptyList())
    }

    private suspend fun givenInternetDataAvailable() {
        given(carRetrofitService.getCarsFull()).willReturn(dataInternet)
    }

    @Test
    fun `returns DB data when internet data missing`() = runBlockingTest {
        givenInternetDataEmpty()
        givenDbDataAvailable()

        val result = sut.getCars()

        assertThat(result).isEqualTo(dataDb)
    }

    private suspend fun givenDbDataAvailable() {
        given(carDao.loadAllCars()).willReturn(dataDb)
    }

    private suspend fun givenInternetDataEmpty() {
        given(carRetrofitService.getCarsFull()).willReturn(emptyList())
    }

    @Test
    fun `return empty data when both internet and DB missing`() = runBlockingTest {
        givenInternetDataEmpty()
        givenDbDataEmpty()

        val result = sut.getCars()

        assertThat(result).isEqualTo(emptyList<List<Car>>())
    }

    @Test
    fun `stores to db when internet data received`() = runBlockingTest {
        givenInternetDataAvailable()

        sut.getCars()

        verify(carDao).insertCar(carInternet)
    }

    @Test
    fun `does not store to DB when internet data received is empty`() = runBlockingTest {
        givenInternetDataEmpty()

        sut.getCars()

        verify(carDao, never()).insertCar(any())
    }
}