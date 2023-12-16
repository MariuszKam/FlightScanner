package com.solvd.repairserviceclasses;

public class Feedback {
    private int feedbackId;
    private String comments;
    private int rating;

    public Feedback(int feedbackId, String comments, int rating) {
        this.feedbackId = feedbackId;
        this.comments = comments;
        this.rating = rating;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
