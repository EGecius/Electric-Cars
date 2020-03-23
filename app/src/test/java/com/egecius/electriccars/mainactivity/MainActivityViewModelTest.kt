package com.egecius.electriccars.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.util.MainCoroutineRule
import com.egecius.electriccars.util.getOrAwaitValue
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    // Sets the main coroutines dispatcher to a TestCoroutineScope for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var carsRepository: CarsRepository

    private lateinit var sut: MainActivityViewModel

    private val car0 = Car("name", "img")
    private val listCar = listOf(car0)

    @Before
    fun setUp() {
        sut = MainActivityViewModel(carsRepository)
    }

    @Test
    fun `live date emits cars list`() = runBlockingTest {
        given(carsRepository.getCars()).willReturn(listCar)

        val result = sut.coroutineLiveData.getOrAwaitValue {
            // After observing, advance the clock to avoid the delay calls.
            mainCoroutineRule.advanceUntilIdle()
        }

        assertThat(result).isEqualTo(listCar)
    }
}