package com.egecius.electriccars.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var carsRepository: CarsRepository
    @Mock
    private lateinit var view: MainActivityView

    @Before
    fun setUp() {
        sut = MainActivityViewModel()
        sut.init(carsRepository)
    }

    @Test
    fun `shows user error message`() {
        givenDataLoadingWillFail()

        sut.startPresenting(view,)

        verify(view).showLoadingError()
    }

    private fun givenDataLoadingWillFail() {
        given(carsRepository.getCars()).willReturn(Single.error(Exception()))
    }

    @Test
    fun `live date emits cars list`() = runBlockingTest {
        given(carsRepository.getCars()).willReturn(listCar)

        val result = sut.coroutineLiveData.value

        // TODO: 22/03/2020 make it pass
        assertThat(result).isEqualTo(car0)
    }
}