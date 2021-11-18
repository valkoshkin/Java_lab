package com.valkoshkin.utils;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.factory.VehicleFactory;
import com.valkoshkin.model.Vehicle;

import java.io.*;

public class VehicleUtils {
    private static VehicleFactory vehicleFactory;

    public static double getAveragePrice(Vehicle vehicle) {
        double sum = 0;
        for (double price : vehicle.getModelsPrices()) {
            sum += price;
        }
        return sum / vehicle.getModelsLength();
    }

    public static void printModelsNamesWithPrices(Vehicle vehicle) {
        String[] modelsNames = vehicle.getModelsNames();
        double[] modelsPrices = vehicle.getModelsPrices();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %s: %f%n", i + 1, modelsNames[i], modelsPrices[i]);
        }
    }

    public static void printModelsNames(Vehicle vehicle) {
        String[] modelsNames = vehicle.getModelsNames();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %s", i + 1, modelsNames[i]);
        }
    }

    public static void printModelsPrices(Vehicle vehicle) {
        double[] modelsPrices = vehicle.getModelsPrices();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            System.out.printf("%d. %f%n", i + 1, modelsPrices[i]);
        }
    }

    public static void setFactory(VehicleFactory vehicleFactory) {
        VehicleUtils.vehicleFactory = vehicleFactory;
    }

    // brandLength: int + brand: byte[] + modelsLength: int +
    // + { modelNameLength: int + modelName: byte[] } * modelsLength + { modelPrice: double } * modelsLength
    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws IOException {
        var stream = new DataOutputStream(out);

        stream.writeInt(vehicle.getBrand().length());
        stream.write(vehicle.getBrand().getBytes());
        stream.writeInt(vehicle.getModelsLength());
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            stream.writeInt(vehicle.getModelsNames()[i].length());
            stream.write(vehicle.getModelsNames()[i].getBytes());
        }
        for (double modelPrice : vehicle.getModelsPrices()) {
            stream.writeDouble(modelPrice);
        }
        stream.flush();
    }

    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        var stream = new DataInputStream(in);

        var brandLength = stream.readInt();
        var brandAsBytesArray = stream.readNBytes(brandLength);
        var brand = new String(brandAsBytesArray);

        var modelsLength = stream.readInt();

        var modelsNames = new String[modelsLength];
        for (int i = 0; i < modelsLength; i++) {
            var modelNameLength = stream.readInt();
            var modelNameAsBytesArray = stream.readNBytes(modelNameLength);
            modelsNames[i] = new String(modelNameAsBytesArray);
        }

        var modelsPrices = new double[modelsLength];
        for (int i = 0; i < modelsLength; i++) {
            modelsPrices[i] = stream.readDouble();
        }

        var vehicle = VehicleUtils.vehicleFactory.createVehicle(brand, modelsLength);
        for (int i = 0; i < modelsLength; i++) {
            vehicle.addModel(modelsNames[i], modelsPrices[i]);
        }

        return vehicle;
    }

    public static void writeVehicle(Vehicle vehicle, Writer out) {
        var writer = new PrintWriter(out);

        writer.println(vehicle.getBrand());
        writer.println(vehicle.getModelsLength());

        StringBuilder modelsNamesBuilder = new StringBuilder();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            modelsNamesBuilder.append(vehicle.getModelsNames()[i]);
            if (i != vehicle.getModelsLength() - 1) modelsNamesBuilder.append(",");
        }
        writer.println(modelsNamesBuilder.toString());

        StringBuilder modelsPricesBuilder = new StringBuilder();
        for (int i = 0; i < vehicle.getModelsLength(); i++) {
            modelsPricesBuilder.append(vehicle.getModelsPrices()[i]);
            if (i != vehicle.getModelsLength() - 1) modelsPricesBuilder.append(",");
        }
        writer.println(modelsPricesBuilder.toString());
        writer.flush();
    }

    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        var reader = new BufferedReader(in);

        var brand = reader.readLine();
        var modelsLength = Integer.parseInt(reader.readLine());
        var modelsNames = reader.readLine().split(",");

        var modelsPricesRaw = reader.readLine().split(",");
        double[] modelsPrices = new double[modelsLength];
        for (int i = 0; i < modelsLength; i++) {
            modelsPrices[i] = Double.parseDouble(modelsPricesRaw[i]);
        }

        var vehicle = VehicleUtils.vehicleFactory.createVehicle(brand, modelsLength);
        for (int i = 0; i < modelsLength; i++) {
            vehicle.addModel(modelsNames[i], modelsPrices[i]);
        }

        return vehicle;
    }

}
