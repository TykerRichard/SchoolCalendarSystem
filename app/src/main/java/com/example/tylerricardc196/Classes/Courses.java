package com.example.tylerricardc196.Classes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;


@Entity(tableName = "Courses")
public class Courses {

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseTitle;
    private Date startDate;
    private Date endDate;
    private String status;
    private String instructorName;
    private int phoneNumber;
    private String emailAddress;



    public Courses(int courseID, String courseTitle, Date startDate, Date endDate, String status) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;


    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseID=" + courseID +
                ", courseTitle='" + courseTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }



    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
