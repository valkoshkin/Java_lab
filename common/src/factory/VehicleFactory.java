package factory;

import model.Vehicle;

public interface VehicleFactory {
    Vehicle createVehicle(String brand, int modelsLength);
}
