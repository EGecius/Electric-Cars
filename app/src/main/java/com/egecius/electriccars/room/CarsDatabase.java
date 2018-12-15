package com.egecius.electriccars.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class}, version = 1)
public abstract class CarsDatabase extends RoomDatabase {

    public abstract CarDao carDao();

    private static volatile CarsDatabase INSTANCE;

    public synchronized static CarsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, CarsDatabase.class, "Sample.db")
                    .build();
        }

        return INSTANCE;
    }

}
