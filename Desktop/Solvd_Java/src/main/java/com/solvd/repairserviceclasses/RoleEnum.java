package com.solvd.repairserviceclasses;

enum RoleEnum {
    TECHNICIAN("Technician", "Responsible for repairing computers"),
    MANAGER("Manager", "Oversees repair operations and manages staff"),
    SUPPORT_STAFF("Support Staff", "Provides assistance and support to customers");

    private final String roleName;
    private final String description;

    RoleEnum(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }
}
