package com.valkoshkin.threads;

public class PrintNamesRunnable implements Runnable {
    private final VehicleSynchronizer synchronizer;

    public PrintNamesRunnable(VehicleSynchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canPrintName()) {
                synchronizer.printName();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
