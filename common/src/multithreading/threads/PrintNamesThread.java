package multithreading.threads;

import model.Vehicle;
import utils.VehicleUtils;

public class PrintNamesThread extends Thread {
    private final Vehicle vehicle;
    private final static String THREAD_NAME = "PrintNamesThread";

    public PrintNamesThread(Vehicle vehicle) {
        super(THREAD_NAME);
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        VehicleUtils.printModelsNames(vehicle);
    }
}
