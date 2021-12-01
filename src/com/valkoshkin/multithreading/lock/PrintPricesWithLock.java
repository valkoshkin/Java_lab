package com.valkoshkin.multithreading.lock;

import com.valkoshkin.model.Vehicle;
import com.valkoshkin.utils.VehicleUtils;

import java.util.concurrent.locks.ReentrantLock;

public class PrintPricesWithLock implements Runnable {
    private final Vehicle vehicle;
    private final ReentrantLock reentrantLock;

    public PrintPricesWithLock(Vehicle vehicle, ReentrantLock reentrantLock) {
        this.vehicle = vehicle;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();
            VehicleUtils.printModelsPrices(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
