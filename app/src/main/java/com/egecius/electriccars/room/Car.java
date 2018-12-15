package com.egecius.electriccars.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity (tableName = "cars")
public class Car {

    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "id")
    public final String id;

    @NonNull
    @ColumnInfo (name = "name")
    public final String name;

    @NonNull
    @ColumnInfo (name = "img")
    public final String img;

    @Ignore
    public Car(@NonNull String name, @NonNull String img) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (!id.equals(car.id)) return false;
        if (!name.equals(car.name)) return false;
        return img.equals(car.img);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + img.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
