package org.example.healthnexus1.dto;

public class RescheduleRequest {

    private String newDate;  // New appointment date in "YYYY-MM-DD" format
    private String newTime;  // New appointment time in "HH:mm:ss" format

    // Getters and Setters
    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }
}
