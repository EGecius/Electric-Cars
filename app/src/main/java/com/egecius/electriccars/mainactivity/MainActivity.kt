package com.egecius.electriccars.mainactivity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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

/** This is very similar to CarListActivity, except it uses simple RecyclerView.Adapter  */
class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var progressBar: ProgressBar

    private lateinit var parentLayout: ViewGroup
    private lateinit var recyclerView: RecyclerView

    private val carRecyclerViewAdapter = CarRecyclerViewAdapter(object : OnCarClickListener {
        override fun onClick(carClick: CarClick) {
            showCarDetailScreen(carClick)
        }
    })

    private fun showCarDetailScreen(carClick: CarClick) {
        CarDetailActivity.start(this, carClick)
    }

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        injectDependencies()
        viewModel.startPresenting(this, this)
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
                viewModel.retryFetching(this)
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