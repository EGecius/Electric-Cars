package com.egecius.electriccars.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.egecius.electriccars.R
import com.egecius.electriccars.databinding.ActivityCarDetailBinding
import com.egecius.electriccars.room.Car
import com.squareup.picasso.Picasso

class CarDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi()
    }

    private fun setUi() {
        val car  = intent.extras[KEY_CAR] as Car
        val binding: ActivityCarDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_car_detail)

        binding.car = car
        Picasso.get().load(car.img).into(binding.image)
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