package com.egecius.electriccars.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface CarDao {

    @Query("SELECT * FROM Cars")
    Flowable<CarRoom> getCar();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCar(CarRoom car);

    @Query("DELETE FROM Cars")
    void deleteAllCars();

}
