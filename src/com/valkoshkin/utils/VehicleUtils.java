package com.valkoshkin.utils;

import com.valkoshkin.model.Vehicle;

public class VehicleUtils {
    public static double getAveragePrice(Vehicle vehicle) {
        double sum = 0;
        for (double price : vehicle.getModelsPrices()) {
            sum += price;
        }
        return sum / vehicle.getModelsLength();
    }

    public static void printModelsNamesWithPrices(Vehicle vehicle) {
        String[] modelsNames = vehicle.getModelsNames();
        double[] modelsPrices = vehicle.getModelsPrices();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %s: %f%n", i + 1, modelsNames[i], modelsPrices[i]);
        }
    }

    public static void printModelsNames(Vehicle vehicle) {
        String[] modelsNames = vehicle.getModelsNames();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %s", i + 1, modelsNames[i]);
        }
    }

    public static void printModelsPrices(Vehicle vehicle) {
        double[] modelsPrices = vehicle.getModelsPrices();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %f%n", i + 1, modelsPrices[i]);
        }
    }

}
