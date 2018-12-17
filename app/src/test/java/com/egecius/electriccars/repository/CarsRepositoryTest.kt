package com.egecius.electriccars.repository

import com.egecius.electriccars.retrofit.CarsRetrofitService
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarDao
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class CarsRepositoryTest {

    private lateinit var mSut: CarsRepository

    @Mock
    lateinit var carsRetrofitService: CarsRetrofitService
    @Mock
    lateinit var carDao: CarDao

    private val carInternet = Car("name internet", "img internet")
    private val dataInternet: List<Car> = listOf(carInternet)


    private val carDb = Car("name db", "img db")
    private val dataDb: List<Car> = listOf(carDb)

    @Before
    fun setUp() {
        mSut = CarsRepository(carsRetrofitService, carDao)
    }

    @Test
    fun `returns data from internet when db data available`() {
        givenInternetDataAvailable()
        givenDbDataAvailable()

        val testObserver = mSut.getCars().test()

        testObserver.assertResult(dataInternet)
    }

    @Test
    fun `returns data from internet when db data missing`() {
        givenInternetDataAvailable()
        givenDbDataEmpty()

        val testObserver = mSut.getCars().test()

        testObserver.assertResult(dataInternet)
    }

    private fun givenDbDataEmpty() {
        given(carDao.loadAllCars()).willReturn(emptyList())
    }

    private fun givenInternetDataAvailable() {
        given(carsRetrofitService.cars()).willReturn(Single.just(dataInternet))
    }

    @Test
    fun `returns DB data when internet data missing`() {
        givenInternetDataEmpty()
        givenDbDataAvailable()

        val testObserver = mSut.getCars().test()

        testObserver.assertResult(dataDb)
    }

    private fun givenDbDataAvailable() {
        given(carDao.loadAllCars()).willReturn(dataDb)
    }

    private fun givenInternetDataEmpty() {
        given(carsRetrofitService.cars()).willReturn(Single.just(emptyList()))
    }

    @Test
    fun `return empty data when both internet and DB missing`() {
        givenInternetDataEmpty()
        givenDbDataEmpty()

        val testObserver = mSut.getCars().test()

        testObserver.assertResult(emptyList())
    }

    @Test
    fun `stores to db when internet data received`() {
        givenInternetDataAvailable()

        mSut.getCars().test()

        verify(carDao).insertCar(carInternet)
    }

    @Test
    fun `does not store to DB when internet data received is empty`() {
        givenInternetDataEmpty()

        mSut.getCars().test()

        verify(carDao, never()).insertCar(any())
    }

}