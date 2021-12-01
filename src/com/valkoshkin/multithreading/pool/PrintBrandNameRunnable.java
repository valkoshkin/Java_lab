package com.valkoshkin.multithreading.pool;

import com.valkoshkin.model.Vehicle;

public class PrintBrandNameRunnable implements Runnable {
    private final Vehicle vehicle;

    public PrintBrandNameRunnable(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println(vehicle.getBrand());
    }
}
