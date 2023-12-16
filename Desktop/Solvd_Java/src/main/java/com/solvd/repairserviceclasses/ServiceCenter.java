package com.solvd.repairserviceclasses;

public class ServiceCenter {
    private String centerName;
    private String location;

    public ServiceCenter(String centerName, String location) {
        this.centerName = centerName;
        this.location = location;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
