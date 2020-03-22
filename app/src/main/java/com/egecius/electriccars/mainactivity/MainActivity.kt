package com.egecius.electriccars.mainactivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.electriccars.R
import com.egecius.electriccars.databinding.ActivityMainBinding
import com.egecius.electriccars.detail.CarDetailActivity
import com.egecius.electriccars.mainactivity.di.DaggerMainActivityComponent
import com.egecius.electriccars.mainactivity.di.MainActivityModule
import com.egecius.electriccars.room.Car
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/** This is very similar to CarListActivity, except it uses simple RecyclerView.Adapter  */
class MainActivity : AppCompatActivity(), MainActivityView {

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
        injectDependencies()
        setupUi()
    }

    private fun setupUi() {
        bindUiWithData()
        showCarsWhenAvailable()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = carRecyclerViewAdapter
    }

    private fun showCarsWhenAvailable() {
        viewModel.coroutineLiveData.observe(this, Observer {
            showCars(it)
        })
    }

    private fun bindUiWithData() {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this
        // Bind ViewModel
        binding.viewModel = viewModel
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
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    override fun showLoadingError() {
        Snackbar.make(parent_layout, "Loading error", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                viewModel.retryFetching(this)
            }
            .show()
        showRecyclerViewOnly()
    }

    override fun showLoadingInProgress() {
        progress_bar.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }
}

interface MainActivityView {
    fun showCars(cars: List<Car>)
    fun showLoadingError()
    fun showLoadingInProgress()
}