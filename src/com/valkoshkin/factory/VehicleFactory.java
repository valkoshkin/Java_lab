package com.valkoshkin.factory;

import com.valkoshkin.model.Vehicle;

public interface VehicleFactory {
    Vehicle createVehicle(String brand, int modelsLength);
}
