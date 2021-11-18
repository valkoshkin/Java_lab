package com.valkoshkin.factory;

import com.valkoshkin.model.Car;
import com.valkoshkin.model.Vehicle;

public class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, int modelsLength) {
        return new Car(brand, modelsLength);
    }
}
