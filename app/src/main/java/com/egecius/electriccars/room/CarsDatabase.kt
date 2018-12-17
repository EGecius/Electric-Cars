package com.egecius.electriccars.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        @Volatile
        private var INSTANCE: CarsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CarsDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, CarsDatabase::class.java, "Sample.db")
                    .build()
            }

            return INSTANCE
        }
    }

}
