package com.egecius.electriccars.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
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
        recyclerView.adapter = CarRecyclerViewAdapter(cars, OnClickListener {
            showDetailScreen()
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showDetailScreen() {
        val intent = Intent(this, CarDetailActivity::class.java)
        startActivity(intent)
    }

}

interface MainActivityView {
    fun showCars(cars: List<Car>)
}