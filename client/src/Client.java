import exceptions.DuplicateModelNameException;
import factory.CarFactory;
import model.Vehicle;
import utils.VehicleUtils;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static final String HOST = "localhost";
    public static final int PORT = 8000;

    public static void main(String[] args) {
        try (var clientSocket = new Socket(HOST, PORT);
             var in = new DataInputStream(clientSocket.getInputStream());
             var out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            VehicleUtils.setVehicleFactory(new CarFactory());
            var vehicles = generateVehicles(2);
            printVehicles(vehicles);

            out.writeObject(vehicles);
            System.out.println("Average models price: " + in.readDouble());

            System.out.print("\nPress any key to disconnect from server...");
            out.write(System.in.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vehicle[] generateVehicles(int size) throws DuplicateModelNameException {
        var vehicles = new Vehicle[size];
        for (int i = 0; i < size; i++) {
            var vehicle = VehicleUtils.getVehicleFactory().createVehicle("Brand-" + i, 5);
            vehicle.addModel("Model-0", Math.random() * 1000);
            vehicle.addModel("Model-1", Math.random() * 1000);
            vehicle.addModel("Model-2", Math.random() * 1000);
            vehicle.addModel("Model-3", Math.random() * 1000);
            vehicle.addModel("Model-4", Math.random() * 1000);
            vehicles[i] = vehicle;
        }
        return vehicles;
    }

    public static void printVehicles(Vehicle[] vehicles) {
        for (var vehicle : vehicles) {
            System.out.println(vehicle.getBrand());
            VehicleUtils.printModelsNamesWithPrices(vehicle);
            System.out.println();
        }
    }
}
