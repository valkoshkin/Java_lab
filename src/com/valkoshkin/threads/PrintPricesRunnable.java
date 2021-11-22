package com.valkoshkin.threads;

public class PrintPricesRunnable implements Runnable {
    private final VehicleSynchronizer synchronizer;

    public PrintPricesRunnable(VehicleSynchronizer synchronizer) {
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
