package com.solvd.repairserviceclasses;

import com.solvd.interfaces.DeviceOperations;

public class Computer extends AbstractDevice implements DeviceOperations {
    private int yearManufactured;
    private String status;

    public Computer(String brand, String model, int yearManufactured) {
        super(brand, model);
        this.yearManufactured = yearManufactured;
    }

    public Computer(String brand, String model, int yearManufactured, String status) {
        super(brand, model);
        this.yearManufactured = yearManufactured;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public int getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    @Override
    public void displayDetails() {
        System.out.println("Computer Details: " + this);
    }

    @Override
    public void startDevice() {
        System.out.println("Starting the computer");
    }

    @Override
    public void stopDevice() {
        System.out.println("Stopping the computer");
    }
}