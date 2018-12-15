package com.egecius.electriccars.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "cars")
public class CarRoom {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "id")
    public String id;

    @NonNull
    @ColumnInfo (name = "name")
    public String name;

    @NonNull
    @ColumnInfo (name = "img")
    public String img;

    public CarRoom(@NonNull String name, @NonNull String img) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.img = img;
    }

    public CarRoom(@NotNull Car car) {
        this.id = UUID.randomUUID().toString();
        this.name = car.getName();
        this.img = car.getImg();
    }

    @Override
    public String toString() {
        return "CarRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

