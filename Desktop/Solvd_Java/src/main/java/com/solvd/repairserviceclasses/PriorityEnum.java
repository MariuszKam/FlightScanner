package com.solvd.repairserviceclasses;

enum PriorityEnum {
    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3),
    URGENT("Urgent", 4) {
        @Override
        public void processUrgentTask() {
            System.out.println("Initiating immediate action for urgent task.");
        }
    };

    private final String priorityName;
    private final int priorityCode;

    PriorityEnum(String priorityName, int priorityCode) {
        this.priorityName = priorityName;
        this.priorityCode = priorityCode;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public int getPriorityCode() {
        return priorityCode;
    }

    public void processUrgentTask() {
        System.out.println("Processing urgent task.");
    }
}
