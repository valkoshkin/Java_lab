package multithreading.queue;

import model.Vehicle;
import utils.VehicleUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class ReadBrandFromFile implements Runnable{
    private final String fileName;
    private final BlockingQueue<Vehicle> blockingQueue;

    public ReadBrandFromFile(String fileName, BlockingQueue<Vehicle> blockingQueue) {
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try (var bufferedReader = new BufferedReader(new FileReader(fileName))) {
            var brandName = bufferedReader.readLine();
            var vehicle = VehicleUtils.getVehicleFactory().createVehicle(brandName, 0);
            this.blockingQueue.put(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
