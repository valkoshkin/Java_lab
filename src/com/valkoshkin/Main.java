package com.valkoshkin;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.ModelPriceOutOfBoundsException;
import com.valkoshkin.factory.CarFactory;
import com.valkoshkin.factory.MotorbikeFactory;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Vehicle;
import com.valkoshkin.utils.VehicleUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static final String filePath = "test-file.bin";

    public static void main(String[] args) {
        try {
            System.out.println("[Car]\n");
            Vehicle car = new Car("BMW", 2);
            addCarModels(car);
            System.out.println("Original vehicle:\n");
            printVehicle(car);
            System.out.println();
            VehicleUtils.setFactory(new CarFactory());
            testByteStream(car);

            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

            System.out.println("[Motorbike]\n");
            Vehicle motorbike = new Motorbike("Yamaha", 2);
            addMotorbikeModels(motorbike);
            System.out.println("Original vehicle:\n");
            printVehicle(motorbike);
            System.out.println();
            VehicleUtils.setFactory(new MotorbikeFactory());
            testByteStream(motorbike);
        } catch (DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
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

    public static void testByteStream(Vehicle vehicle) {
        try {
            VehicleUtils.outputVehicle(vehicle, new FileOutputStream(filePath, false));
            var vehicleFromInputStream = VehicleUtils.inputVehicle(new FileInputStream(filePath));
            System.out.println("Vehicle from byte stream:\n");
            printVehicle(vehicleFromInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void printVehicle(Vehicle vehicle) {
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Models length: " + vehicle.getModelsLength());
        System.out.println("Models:");
        VehicleUtils.printModelsNamesWithPrices(vehicle);
    }
}
