package com.egecius.electriccars.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.detail.CarDetailActivity
import com.egecius.electriccars.mainactivity.di.DaggerMainActivityComponent
import com.egecius.electriccars.mainactivity.di.MainActivityModule
import com.egecius.electriccars.room.Car
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var presenter : MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()
        presenter.startPresenting(this, this)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().injectInto(this)
    }

    override fun showCars(cars: List<Car>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = CarRecyclerViewAdapter(cars, object : CarRecyclerViewAdapterOnClickListener{
            override fun onClick(car: Car) {
                showDetailScreen(car)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showDetailScreen(car: Car) {
        CarDetailActivity.start(this, car)
    }

}

interface MainActivityView {
    fun showCars(cars: List<Car>)
}