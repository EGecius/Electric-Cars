package com.egecius.electriccars.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM cars")
    List<CarRoom> getAllCars();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCar(CarRoom car);

    @Query("DELETE FROM Cars")
    void deleteAllCars();

}
