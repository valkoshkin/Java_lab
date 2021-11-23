package com.valkoshkin.multithreading.threads;

import com.valkoshkin.model.Vehicle;
import com.valkoshkin.utils.VehicleUtils;

public class PrintPricesThread extends Thread {
    private final Vehicle vehicle;
    private final static String THREAD_NAME = "PrintPricesThread";

    public PrintPricesThread(Vehicle vehicle) {
        super(THREAD_NAME);
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        VehicleUtils.printModelsPrices(vehicle);
    }
}
