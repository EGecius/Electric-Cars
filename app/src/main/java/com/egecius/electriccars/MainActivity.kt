package com.egecius.electriccars

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printCars()
    }

    private fun printCars() {
        val cars = CarsRepository().carsList
        Log.i("Eg:MainActivity:18", "printCars cars $cars")
    }
}
