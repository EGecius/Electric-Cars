package com.egecius.electriccars.mainactivity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        injectDependencies()
        presenter.startPresenting(this, this)
    }

    private fun setupUi() {
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().injectInto(this)
    }

    override fun showCars(cars: List<Car>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarRecyclerViewAdapter(cars, object : CarRecyclerViewAdapterOnClickListener {
            override fun onClick(car: Car) {
                showDetailScreen(car)
            }
        })
    }

    private fun showDetailScreen(car: Car) {
        CarDetailActivity.start(this, car)
    }

}

interface MainActivityView {
    fun showCars(cars: List<Car>)
}