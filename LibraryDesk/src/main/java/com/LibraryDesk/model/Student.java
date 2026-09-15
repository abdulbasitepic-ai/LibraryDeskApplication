package com.LibraryDesk.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Student {
@Id

private int studentId;
private String studentName;

    public Student() {

    }

      public Student( int studentId ,  String studentName ) {
          this.studentName = studentName;
          this.studentId = studentId;

      }


    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
