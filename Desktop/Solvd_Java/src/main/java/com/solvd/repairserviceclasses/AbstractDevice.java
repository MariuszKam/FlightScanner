package com.solvd.repairserviceclasses;

import java.util.Objects;

public abstract class AbstractDevice {
    protected String brand;
    protected String model;
    protected double baseRepairCost;

    public AbstractDevice(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract void displayDetails();

    public double getBaseRepairCost() {
        return baseRepairCost;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }


    @Override
    public String toString() {
        return "Brand: " + brand + ", Model: " + model;
    }


    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractDevice that = (AbstractDevice) obj;
        return Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }
}
