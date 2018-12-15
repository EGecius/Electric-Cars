package com.egecius.electriccars

import com.egecius.electriccars.room.Car
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import java.util.*

class CarsFileReader {

    internal val cars: List<Car>
        get() {

            val type = Types.newParameterizedType(List::class.java, Car::class.java)
            val adapter = Moshi.Builder().build().adapter<List<Car>>(type)
            var cars: List<Car> = ArrayList()
            try {
                cars = adapter.fromJson(this.carsAsString)!!
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return cars
        }

    private val carsAsString: String
        get() {
            val inputStream = javaClass.getResourceAsStream("/" + "electric_cars.json")
            return Scanner(inputStream!!).useDelimiter("\\A").next()
        }
}
