package com.egecius.electriccars.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.egecius.electriccars.R
import com.egecius.electriccars.room.Car
import com.squareup.picasso.Picasso

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)

        val car  = intent.extras[KEY_CAR] as Car

        setUi(car)
    }

    private fun setUi(car: Car) {
        findViewById<TextView>(R.id.title).text = car.name
        val imageView = findViewById<ImageView>(R.id.image)
        Picasso.get().load(car.img).into(imageView)

        // TODO: 20/12/2018 set data using data binding
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