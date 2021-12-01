package com.valkoshkin.multithreading.synchronizer;

public class PrintPricesWithSynchronizer implements Runnable {
    private final VehicleSynchronizer synchronizer;

    public PrintPricesWithSynchronizer(VehicleSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintPrice()) {
                synchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
