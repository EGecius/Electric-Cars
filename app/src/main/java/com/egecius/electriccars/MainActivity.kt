package com.egecius.electriccars

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val cars = CarsRepository().carsList
        recyclerView.adapter = CarRecyclerViewAdapter(cars)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
