package com.egecius.electriccars;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarsRepository {

    List<Car> getCarsList() {

        String carsJsonResponse = getCars();
        Type type = Types.newParameterizedType(List.class, Car.class);
        JsonAdapter<List<Car>> adapter = new Moshi.Builder().build().adapter(type);
        List<Car> cars = new ArrayList<>();
        try {
            cars = adapter.fromJson(carsJsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cars;
    }

    private String getCars() {
        InputStream inputStream = getClass().getResourceAsStream("/" + "electric_cars.json");
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }
}
