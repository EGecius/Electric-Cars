package com.egecius.electriccars.paging

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.egecius.electriccars.room.Car
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarListViewModelTest {

    private lateinit var mSut: CarListViewModel

    @Mock
    lateinit var carsLiveData : LiveData<PagedList<Car>>
    @Mock
    lateinit var view: CarListViewModel.View
    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    @Mock
    lateinit var pagedListCars: PagedList<Car>
    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Observer<PagedList<Car>>>

    @Before
    fun setUp() {
        mSut = CarListViewModel()
        mSut.init(carsLiveData)
    }

    @Test
    fun `shows list of cars`() {
        mSut.startPresenting(view, lifecycleOwner)

        whenLiveDataReturnsResult()

        verify(view).showCars(pagedListCars)
    }

    private fun whenLiveDataReturnsResult() {
        verify(carsLiveData).observe(eq(lifecycleOwner), argumentCaptor.capture())
        argumentCaptor.value.onChanged(pagedListCars)
    }

}