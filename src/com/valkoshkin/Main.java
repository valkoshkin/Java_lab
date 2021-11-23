package com.valkoshkin;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.model.Car;
import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Vehicle;
import com.valkoshkin.multithreading.lock.PrintNamesWithLock;
import com.valkoshkin.multithreading.lock.PrintPricesWithLock;
import com.valkoshkin.multithreading.pool.PrintBrandNameRunnable;
import com.valkoshkin.multithreading.synchronizer.PrintNamesWithSynchronizer;
import com.valkoshkin.multithreading.synchronizer.PrintPricesWithSynchronizer;
import com.valkoshkin.multithreading.synchronizer.VehicleSynchronizer;
import com.valkoshkin.multithreading.threads.PrintNamesThread;
import com.valkoshkin.multithreading.threads.PrintPricesThread;
import com.valkoshkin.utils.VehicleUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("[Car]\n");
            Vehicle car = new Car("BMW", 2);
            addCarModels(car);
            System.out.println("Original vehicle:\n");
            printVehicle(car);

            System.out.println("\nTest 'Thread' priorities (Task 1):");
            testThreadPriorities(car);

            System.out.println("\nTest synchronizer (Task 2):");
            testSynchronizer(car);

            System.out.println("\nTest lock (Task 3):");
            testLock(car);

            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

            System.out.println("[Motorbike]\n");
            Vehicle motorbike = new Motorbike("Yamaha", 2);
            addMotorbikeModels(motorbike);
            System.out.println("Original vehicle:\n");
            printVehicle(motorbike);

            System.out.println("\nTest 'Thread' class successors (Task 1):");
            testThreadPriorities(motorbike);

            System.out.println("\nTest synchronizer (Task 2):");
            testSynchronizer(motorbike);

            System.out.println("\nTest lock (Task 3):");
            testLock(motorbike);

            System.out.println("\nTest thread pool (Task 4):");
            testThreadPool();
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

    public static void testThreadPriorities(Vehicle vehicle) {
        var printPricesThread = new PrintPricesThread(vehicle);
        var printNamesThread = new PrintNamesThread(vehicle);

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

    public static void testSynchronizer(Vehicle vehicle) {
        var synchronizer = new VehicleSynchronizer(vehicle);
        var printPricesThread = new Thread(new PrintPricesWithSynchronizer(synchronizer));
        var printNamesThread = new Thread(new PrintNamesWithSynchronizer(synchronizer));

        printPricesThread.start();
        printNamesThread.start();

        try {
            printPricesThread.join();
            printNamesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testLock(Vehicle vehicle) {
        var reentrantLock = new ReentrantLock();
        var printNamesThread = new Thread(new PrintNamesWithLock(vehicle, reentrantLock));
        var printPricesThread = new Thread(new PrintPricesWithLock(vehicle, reentrantLock));

        printNamesThread.start();
        printPricesThread.start();

        try {
            printNamesThread.join();
            printPricesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testThreadPool() {
        var firstCar = new Car("AUDI", 1);
        var secondCar = new Car("Tesla", 1);
        var firstBike = new Motorbike("Suzuki", 1);
        var secondBike = new Motorbike("Kawasaki", 1);

        var executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new PrintBrandNameRunnable(firstCar));
        executorService.submit(new PrintBrandNameRunnable(secondCar));
        executorService.submit(new PrintBrandNameRunnable(firstBike));
        executorService.submit(new PrintBrandNameRunnable(secondBike));
        executorService.shutdown();
    }
}
