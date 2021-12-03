package multithreading.synchronizer;

import model.Vehicle;

public class VehicleSynchronizer {
    private final Vehicle vehicle;
    private volatile int current = 0;
    private boolean isNamePrinted = false;

    public VehicleSynchronizer(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    synchronized public void printPrice() throws InterruptedException {
        double[] modelsPrices = vehicle.getModelsPrices();
        if (!canPrintPrice()) throw new InterruptedException();
        while (!isNamePrinted) {
            wait();
        }
        System.out.println("Print price: " + modelsPrices[current++]);
        isNamePrinted = false;
        notifyAll();
    }

    synchronized public void printName() throws InterruptedException {
        String[] modelsNames = vehicle.getModelsNames();
        if (!canPrintName()) throw new InterruptedException();
        while (isNamePrinted) {
            wait();
        }
        System.out.println("Print model: " + modelsNames[current]);
        isNamePrinted = true;
        notifyAll();
    }

    public boolean canPrintPrice() {
        return current < vehicle.getModelsLength();
    }

    public boolean canPrintName() {
        return (!isNamePrinted && current < vehicle.getModelsLength()) ||
                (isNamePrinted && current < vehicle.getModelsLength() - 1);
    }
}

