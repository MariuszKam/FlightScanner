package com.solvd.repairserviceclasses;

import com.solvd.interfaces.InventoryManagement;

public class CustomerRelationManager extends Employees implements InventoryManagement {
    private String customerSpecialization;

    public CustomerRelationManager(String name, int employeeId, String departmentName, String customerSpecialization) {
        super(name, employeeId, departmentName);
        this.customerSpecialization = customerSpecialization;
    }

    public String getCustomerSpecialization() {
        return customerSpecialization;
    }

    public void setCustomerSpecialization(String customerSpecialization) {
        this.customerSpecialization = customerSpecialization;
    }

    @Override
    public void performDuties() {
        System.out.println("Customer Relation Manager performing duties");
    }

    @Override
    public void updateInventory() {
        System.out.println("Updating inventory records");
    }

    @Override
    public void checkStockAvailability() {
        System.out.println("Checking stock availability");
    }
}
