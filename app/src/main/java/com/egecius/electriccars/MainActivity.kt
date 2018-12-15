package com.egecius.electriccars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var presenter : MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
    }

    private fun initPresenter() {
        presenter = ViewModelProviders.of(this).get(MainActivityPresenter::class.java)
        val myApplication = application as MyApplication
        myApplication.getMockServerUrl()
            .flatMapCompletable { startPresenting(it) }
            .subscribe()
    }

    private fun startPresenting(mockServerUrl: String): Completable {
        return Completable.fromCallable {
            presenter.startPresenting(this, this, mockServerUrl)
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