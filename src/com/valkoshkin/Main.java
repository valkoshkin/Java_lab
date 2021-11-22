package com.valkoshkin;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Vehicle;
import com.valkoshkin.threads.PrintNamesThread;
import com.valkoshkin.threads.PrintPricesThread;
import com.valkoshkin.utils.VehicleUtils;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("[Car]\n");
            Vehicle car = new Car("BMW", 2);
            addCarModels(car);
            System.out.println("Original vehicle:\n");
            printVehicle(car);

            System.out.println("\nTest 'Thread' class successors (Task 1):");
            testThreadSuccessors(car);

            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

            System.out.println("[Motorbike]\n");
            Vehicle motorbike = new Motorbike("Yamaha", 2);
            addMotorbikeModels(motorbike);
            System.out.println("Original vehicle:\n");
            printVehicle(motorbike);

            System.out.println("\nTest 'Thread' class successors (Task 1):");
            testThreadSuccessors(motorbike);
        } catch (Exception e) {
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

    public static void printVehicle(Vehicle vehicle) {
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Models length: " + vehicle.getModelsLength());
        System.out.println("Models:");
        VehicleUtils.printModelsNamesWithPrices(vehicle);
    }

    public static void testThreadSuccessors(Vehicle vehicle) {
        PrintPricesThread printPricesThread = new PrintPricesThread(vehicle);
        PrintNamesThread printNamesThread = new PrintNamesThread(vehicle);

        printPricesThread.setPriority(Thread.MAX_PRIORITY);
        printNamesThread.setPriority(Thread.MIN_PRIORITY);

        printPricesThread.start();
        printNamesThread.start();

        try {
            printPricesThread.join();
            printNamesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
