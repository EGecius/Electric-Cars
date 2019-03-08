package com.egecius.electriccars.mainactivity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.egecius.electriccars.repository.CarsLiveData
import com.egecius.electriccars.repository.Result
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.eq
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    private lateinit var mSut: MainActivityPresenter

    private val carList = listOf(Car("Tesla 3", "img_url"))
    private val resultSuccess: Result<List<Car>> = Result(carList, null)
    private val resultError: Result<List<Car>> = Result(null, Throwable())

    @Mock
    private lateinit var carsLiveData: CarsLiveData
    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    private lateinit var view: MainActivityView

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Observer<Result<List<Car>>>>

    @Before
    fun setUp() {
        mSut = MainActivityPresenter()
        mSut.init(carsLiveData)
    }

    @Test
    fun `shows user error message`() {
        mSut.startPresenting(view, lifecycleOwner)

        whenLiveDataReturnsError()

        verify(view).showLoadingError()
    }

    private fun whenLiveDataReturnsError() {
        verify(carsLiveData).observe(eq(lifecycleOwner), argumentCaptor.capture())
        val result = resultError
        argumentCaptor.value.onChanged(result)
    }

    @Test
    fun `show list of cars`() {
        mSut.startPresenting(view, lifecycleOwner)

        whenLiveDataReturnsListOfCars()

        verify(view).showCars(carList)
    }

    private fun whenLiveDataReturnsListOfCars() {
        verify(carsLiveData).observe(eq(lifecycleOwner), argumentCaptor.capture())
        val result = resultSuccess
        argumentCaptor.value.onChanged(result)
    }

}