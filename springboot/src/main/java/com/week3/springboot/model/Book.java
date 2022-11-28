package com.week3.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(
    name = "book"
)
public class Book {
    @Id
    @Column(
        name = "book_id"
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int bookId;
    @Column(
        name = "class_id"
    )
    private int classId;
    @Column(
        name = "book_status"
    )
    private boolean bookStatus;

    public Book() {
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isBookStatus() {
        return this.bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String toString() {
        return "Book [bookId=" + this.bookId + ", classId=" + this.classId + "]";
    }
}
