package com.example.tylerricardc196.Classes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Instructor")
public class Instructor {

    @PrimaryKey(autoGenerate = true)
    private int instructorID;
    private String instructorName;
    private int instructorPhoneNumber;
    private String instructorEmail;

    public Instructor(int instructorID, String instructorName, int instructorPhoneNumber, String instructorEmail) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.instructorPhoneNumber = instructorPhoneNumber;
        this.instructorEmail = instructorEmail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorID=" + instructorID +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhoneNumber=" + instructorPhoneNumber +
                ", instructorEmail='" + instructorEmail + '\'' +
                '}';
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getInstructorPhoneNumber() {
        return instructorPhoneNumber;
    }

    public void setInstructorPhoneNumber(int instructorPhoneNumber) {
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
