package com.egecius.electriccars;

import java.io.InputStream;
import java.util.Scanner;

public class CarsRepository {

    String getCars() {
        InputStream inputStream = getClass().getResourceAsStream("/" + "electric_cars.json");
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }
}
