package com.egecius.electriccars

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import java.util.*

class CarsRepository {

    internal val carsList: List<Car>
        get() {

            val type = Types.newParameterizedType(List::class.java, Car::class.java)
            val adapter = Moshi.Builder().build().adapter<List<Car>>(type)
            var cars: List<Car> = ArrayList()
            try {
                cars = adapter.fromJson(this.cars)!!
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return cars
        }

    private val cars: String
        get() {
            val inputStream = javaClass.getResourceAsStream("/" + "electric_cars.json")
            return Scanner(inputStream!!).useDelimiter("\\A").next()
        }
}
