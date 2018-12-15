package com.egecius.electriccars.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "cars")
public class CarRoom {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "name")
    public String name;

    @NonNull
    @ColumnInfo (name = "img")
    public String img;

    public CarRoom(@NonNull String name, @NonNull String img) {
        this.name = name;
        this.img = img;
    }

    public CarRoom(@NotNull Car car) {
        this.name = car.getName();
        this.img = car.getImg();
    }

    @Override @NonNull
    public String toString() {
        return "CarRoom{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

