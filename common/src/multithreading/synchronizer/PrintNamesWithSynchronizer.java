package multithreading.synchronizer;

public class PrintNamesWithSynchronizer implements Runnable {
    private final VehicleSynchronizer synchronizer;

    public PrintNamesWithSynchronizer(VehicleSynchronizer synchronizer) {
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
