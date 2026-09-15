package com.LibraryDesk.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;




import java.time.LocalDate;

@Entity
public class BorrowRecord {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Book book;
    @ManyToOne
    private Student student;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double lateFees;

    public BorrowRecord() {
    }



    public BorrowRecord(Book book, Student student, LocalDate issueDate, LocalDate dueDate) {
        this.book = book;
        this.student = student;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }


    public Student getStudent() {
        return student;
    }

    public Book getBook() {
        return book;
    }

    public double getLateFees() {
        return lateFees;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public long getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setLateFees(double lateFees) {
        this.lateFees = lateFees;
    }
}
