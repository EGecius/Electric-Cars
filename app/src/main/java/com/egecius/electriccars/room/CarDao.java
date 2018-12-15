package com.egecius.electriccars.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface CarDao {

    @Query("SELECT * FROM Cars")
    Flowable<Car> getCar();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCar(Car car);

    @Query("DELETE FROM Cars")
    void deleteAllCars();

}
