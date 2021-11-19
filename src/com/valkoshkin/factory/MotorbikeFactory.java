package com.valkoshkin.factory;

import com.valkoshkin.model.Motorbike;
import com.valkoshkin.model.Vehicle;

public class MotorbikeFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, int modelsLength) {
        return new Motorbike(brand, modelsLength);
    }
}
