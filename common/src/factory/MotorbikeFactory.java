package factory;

import model.Motorbike;
import model.Vehicle;

public class MotorbikeFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, int modelsLength) {
        return new Motorbike(brand, modelsLength);
    }
}
