package com.example.tylerricardc196.Classes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Assignments")
public class Assignments {

    @PrimaryKey(autoGenerate = true)
    private int assignmentID;
    private String assignmentName;
    private String type;
    private String endDate;

    public Assignments(int assignmentID, String assignmentName, String type, String endDate) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.type = type;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Assignments{" +
                "assignmentID=" + assignmentID +
                ", assignmentName='" + assignmentName + '\'' +
                ", type='" + type + '\'' +
                ", endDate=" + endDate +
                '}';
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
