package com.valkoshkin.model;

import com.valkoshkin.exceptions.DuplicateModelNameException;
import com.valkoshkin.exceptions.NoSuchModelNameException;

public interface Vehicle {
    String getBrand();
    void setBrand(String brand);
    int getModelsLength();
    String[] getModelsNames();
    void setModelNameByName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;
    double getModelPriceByName(String name) throws NoSuchModelNameException;
    void setModelPriceByName(String name, double price) throws NoSuchModelNameException;
    double[] getModelsPrices();
    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;
}