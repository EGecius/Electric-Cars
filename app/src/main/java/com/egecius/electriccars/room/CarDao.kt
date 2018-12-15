package com.egecius.electriccars.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun loadAllCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Query("DELETE FROM Cars")
    fun deleteAllCars()

}
