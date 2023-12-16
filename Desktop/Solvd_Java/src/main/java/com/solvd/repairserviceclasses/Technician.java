package com.solvd.repairserviceclasses;

import com.solvd.interfaces.RepairService;

public class Technician extends Employees implements RepairService {
    private int experienceYears;

    public Technician(String name, int employeeId, String departmentName, int experienceYears) {
        super(name, employeeId, departmentName);
        this.experienceYears = experienceYears;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public void performDuties() {
        System.out.println("Technician performing duties");
    }

    @Override
    public void diagnoseIssue() {
        System.out.println("Diagnosing and identifying the issue");
    }

    @Override
    public void performRepair() {
        System.out.println("Performing the repair");
    }

}
