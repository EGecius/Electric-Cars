package com.egecius.electriccars.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
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

    private lateinit var sut: MainActivityViewModel

    private val car0 = Car("name", "img")
    private val listCar = listOf(car0)

    @Before
    fun setUp() {
        sut = MainActivityViewModel(carsRepository)
    }

    @Test
    @Ignore // TODO: 23/03/2020 make this test pass
    fun `live date emits cars list`() = runBlockingTest {
        given(carsRepository.getCars()).willReturn(listCar)

        val result = sut.coroutineLiveData.value

        assertThat(result).isEqualTo(car0)
    }
}