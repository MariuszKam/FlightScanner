package com.solvd.repairserviceclasses;

enum ComputerStatusEnum {
    BROKEN("Broken", 1) {
        @Override
        public void repairAction() {
            System.out.println("Performing diagnostic tests and repairing components.");
        }
    },
    IN_PROGRESS("In Progress", 2) {
        @Override
        public void repairAction() {
            System.out.println("Currently working on fixing the computer.");
        }
    },
    FIXED("Fixed", 3) {
        @Override
        public void repairAction() {
            System.out.println("Computer is repaired and ready for delivery.");
        }
    };

    private final String statusName;
    private final int statusCode;

    ComputerStatusEnum(String statusName, int statusCode) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public abstract void repairAction();
}
