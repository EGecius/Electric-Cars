package com.egecius.electriccars.mainactivity

import com.egecius.electriccars.app.TestSchedulers
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    private lateinit var mSut: MainActivityViewModel

    private val carList = listOf(Car("Tesla 3", "img_url"))

    @Mock
    private lateinit var carsRepository: CarsRepository
    @Mock
    private lateinit var view: MainActivityView

    @Before
    fun setUp() {
        mSut = MainActivityViewModel()
        mSut.init(carsRepository, TestSchedulers())
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