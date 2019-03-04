package com.egecius.electriccars.mainactivity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.detail.CarDetailActivity
import com.egecius.electriccars.mainactivity.di.DaggerMainActivityComponent
import com.egecius.electriccars.mainactivity.di.MainActivityModule
import com.egecius.electriccars.room.Car
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var progressBar: ProgressBar

    private lateinit var parentLayout: ViewGroup
    private lateinit var recyclerView: RecyclerView

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(car: Car, imageView: ImageView) {
            showCarDetailScreen(car, imageView)
        }
    })

    private fun showCarDetailScreen(car: Car, imageView: ImageView) {
        CarDetailActivity.start(this, car, imageView)
    }

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
        parentLayout = findViewById(R.id.parent_layout)
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = carRecyclerViewAdapter
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build().injectInto(this)
    }

    override fun showCars(cars: List<Car>) {
        showRecyclerViewOnly()
        carRecyclerViewAdapter.setData(cars)
    }

    private fun showRecyclerViewOnly() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showLoadingError() {
        Snackbar.make(parentLayout, "Loading error", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                presenter.retryFetching()
            }
            .show()
        showRecyclerViewOnly()
    }

    override fun showLoadingInProgress() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

}

interface MainActivityView {
    fun showCars(cars: List<Car>)
    fun showLoadingError()
    fun showLoadingInProgress()
}