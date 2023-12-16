package com.solvd.repairserviceclasses;

public class RepairJob {
    private int jobId;
    private String issue;
    private boolean completed;

    public RepairJob(int jobId, String issue) {
        this.jobId = jobId;
        this.issue = issue;
        this.completed = false;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
