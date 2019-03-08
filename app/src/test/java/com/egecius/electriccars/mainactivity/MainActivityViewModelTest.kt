package com.egecius.electriccars.mainactivity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.repository.Result
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var mSut: MainActivityViewModel

    private val carList = listOf(Car("Tesla 3", "img_url"))
    private val resultSuccess: Result<List<Car>> = Result(carList, null)
    private val resultError: Result<List<Car>> = Result(null, Throwable())

    @Mock
    private lateinit var carsRepository: CarsRepository
    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    private lateinit var view: MainActivityView

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Observer<Result<List<Car>>>>

    @Before
    fun setUp() {
        mSut = MainActivityViewModel()
        mSut.init(carsRepository)
    }

    @Test
    fun `shows user error message`() {
        givenDataLoadingWillFail()

        mSut.startPresenting(view)

        verify(view).showLoadingError()
    }

    private fun givenDataLoadingWillFail() {
        given(carsRepository.getCars()).willReturn(Single.error(Exception()))
    }

    @Test
    fun `show list of cars`() {
        givenDataLoadingWillSucceed()

        mSut.startPresenting(view)

        verify(view).showCars(carList)
    }

    private fun givenDataLoadingWillSucceed() {
        given(carsRepository.getCars()).willReturn(Single.just(carList))
    }

    @Test
    fun `retries fetching`() {
        givenDataLoadingWillFail()
        mSut.startPresenting(view)

        mSut.retryFetching()

        verify(view).showLoadingInProgress()
    }

    @Test
    fun `show loading dialog when retrying`() {
        givenDataLoadingWillFail()
        mSut.startPresenting(view)

        mSut.retryFetching()

        verify(view).showLoadingInProgress()
    }

}