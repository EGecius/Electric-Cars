package com.egecius.electriccars.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.mainactivity.di.DaggerMainActivityComponent
import com.egecius.electriccars.mainactivity.di.MainActivityModule
import com.egecius.electriccars.repository.CarsRepository
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car
import com.egecius.electriccars.room.CarsDatabase
import com.egecius.electriccars.various.MyApplication
import io.reactivex.Completable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var presenter : MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()
        initPresenter()

        Log.i("Eg:MainActivity:31", "onCreate presenter " + presenter)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().injectInto(this)
    }

    private fun initPresenter() {
        val myApplication = application as MyApplication
        myApplication.getMockServerUrl()
            .flatMapCompletable { startPresenting(it) }
            .subscribe()
    }

    private fun startPresenting(mockServerUrl: String): Completable {

        val carsDatabase = CarsDatabase.getInstance(this)
        val carsRepository = CarsRepository(
            RetrofitAdapter(mockServerUrl).setupRetrofit(),
            carsDatabase.carDao()
        )

        return Completable.fromCallable {
            presenter.startPresenting(this, this, carsRepository)
        }
    }

    override fun showCars(cars: List<Car>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = CarRecyclerViewAdapter(cars)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}


interface MainActivityView {
    fun showCars(cars: List<Car>)
}