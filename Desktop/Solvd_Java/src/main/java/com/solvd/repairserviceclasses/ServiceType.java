package com.solvd.repairserviceclasses;

enum ServiceType {
    DIAGNOSTIC("Diagnostic", "Identifying computer issues"),
    REPAIR("Repair", "Fixing hardware and software problems"),
    MAINTENANCE("Maintenance", "Preventive maintenance and cleaning");

    private final String typeName;
    private final String description;

    ServiceType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription() {
        return description;
    }
}
