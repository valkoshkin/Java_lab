import model.Vehicle;
import utils.VehicleUtils;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class Server {
    public static final int PORT = 8000;

    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started.\n");
            while (true) {
                try (var clientSocket = serverSocket.accept();
                     var in = new ObjectInputStream(clientSocket.getInputStream());
                     var out = new DataOutputStream(clientSocket.getOutputStream())) {
                    System.out.println("Client connected.");
                    var vehicles = (Vehicle[]) in.readObject();
                    var averagePrice = VehicleUtils.getVehiclesAveragePrice(vehicles);
                    System.out.println("Average models price: " + averagePrice);
                    out.writeDouble(averagePrice);

                    in.read();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client disconnected");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
