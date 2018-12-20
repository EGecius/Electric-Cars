package com.egecius.electriccars.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.egecius.electriccars.R
import com.egecius.electriccars.room.Car

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        val car  = intent.extras[KEY_CAR] as Car

        Log.i("Eg:CarDetailActivity:18", "onCreate car " + car)
    }

    companion object {

        const val KEY_CAR = "key_car"

    	fun start(originActivity: Activity, car: Car) {
            val intent = Intent(originActivity, CarDetailActivity::class.java)
            intent.putExtra(KEY_CAR, car)
            originActivity.startActivity(intent)
        }
    }
}