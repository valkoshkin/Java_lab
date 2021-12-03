package factory;

import model.Car;
import model.Vehicle;

public class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, int modelsLength) {
        return new Car(brand, modelsLength);
    }
}
