package com.valkoshkin;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.ModelPriceOutOfBoundsException;
import com.valkoshkin.exceptions.NoSuchModelNameException;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Vehicle;
import com.valkoshkin.utils.VehicleUtils;

public class Main {

    public static void main(String[] args) {
        try {
            Vehicle car = new Car("BMW", 0);
            Vehicle motorbike = new Motorbike("Yamaha", 2);

            System.out.println("[Car]\n");
            addCarModels(car);
            System.out.println("Brand: " + car.getBrand());
            System.out.println("\nModels length: " + car.getModelsLength());
            System.out.println("Models:");
            VehicleUtils.printModelsNamesWithPrices(car);
            System.out.println("\nAverage model price: " + VehicleUtils.getAveragePrice(car));

            String carModelNameToDelete = "C12";
            System.out.println("\nDelete model with name: " + carModelNameToDelete);
            car.deleteModel(carModelNameToDelete);

            String carModelNameToGetPrice = "A3";
            System.out.printf("\nPrice of '%s' model: %f\n", carModelNameToGetPrice, car.getModelPriceByName(carModelNameToGetPrice));
            car.getModelPriceByName(carModelNameToGetPrice);

            double carNewModelPrice = 999.09;
            System.out.printf("\nUpdate '%s' model price to price %f.\n", carModelNameToGetPrice, carNewModelPrice);
            car.setModelPriceByName(carModelNameToGetPrice, carNewModelPrice);

            System.out.println("\nModels length: " + car.getModelsLength());
            System.out.println("Models:");
            VehicleUtils.printModelsNamesWithPrices(car);

            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

            System.out.println("[Motorbike]\n");

            addMotorbikeModels(motorbike);
            System.out.println("Brand: " + motorbike.getBrand());
            System.out.println("\nModels length: " + motorbike.getModelsLength());
            System.out.println("Models:");
            VehicleUtils.printModelsNamesWithPrices(motorbike);
            System.out.println("\nAverage model price: " + VehicleUtils.getAveragePrice(motorbike));

            String bikeModelNameToDelete = "R-8";
            System.out.println("\nDelete model with name: " + bikeModelNameToDelete);
            motorbike.deleteModel(bikeModelNameToDelete);

            String bikeModelNameToGetPrice = "T-41";
            System.out.printf("\nPrice of '%s' model: %f\n", bikeModelNameToGetPrice, motorbike.getModelPriceByName(bikeModelNameToGetPrice));
            motorbike.getModelPriceByName(bikeModelNameToGetPrice);

            String bikeNewModelName = "NT-94";
            System.out.printf("\nUpdate '%s' model name to '%s'.\n", bikeModelNameToGetPrice, bikeNewModelName);
            motorbike.setModelNameByName(bikeModelNameToGetPrice, bikeNewModelName);

            System.out.println("\nModels length: " + motorbike.getModelsLength());
            System.out.println("Models:");
            VehicleUtils.printModelsNamesWithPrices(motorbike);
        } catch (NoSuchModelNameException | DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCarModels(Vehicle vehicle) throws DuplicateModelNameException {
        vehicle.addModel("X5", 123);
        vehicle.addModel("X6", 834);
        vehicle.addModel("A3", 172.99);
        vehicle.addModel("C12", 534.12);
    }

    public static void addMotorbikeModels(Vehicle vehicle) throws DuplicateModelNameException {
        vehicle.addModel("M-12", 104.33);
        vehicle.addModel("R-8", 542);
        vehicle.addModel("ZX-2", 712);
        vehicle.addModel("T-41", 404);
    }
}
