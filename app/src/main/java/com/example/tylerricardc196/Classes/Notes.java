package com.example.tylerricardc196.Classes;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "Notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int noteID;
    private String note;
    private int courseID;

    public Notes(int noteID, String note, int courseID) {
        this.noteID = noteID;
        this.note = note;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteID=" + noteID +
                ", note='" + note + '\'' +
                ", classID=" + courseID +
                '}';
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
