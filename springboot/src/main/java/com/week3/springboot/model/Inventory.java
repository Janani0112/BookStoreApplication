package com.week3.springboot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "inventory"
)
public class Inventory {
    @Id
    @Column(
        name = "class_id"
    )
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Integer classId;
    @Column(
        name = "book_name"
    )
    private String bookName;
    @Column(
        name = "book_author"
    )
    private String bookAuthor;
    @Column(
        name = "book_price"
    )
    private int bookPrice;
    @Column(
        name = "book_category"
    )
    private String bookCategory;
    @Column(
        name = "book_count"
    )
    private int bookCount;
    @Column(
        name = "count_of_likes"
    )
    private int bookLikes = 0;

    public Inventory() {
    }

    public Integer getClassId() {
        return this.classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return this.bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPrice() {
        return this.bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getBookCount() {
        return this.bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getBookLikes() {
        return this.bookLikes;
    }

    public void setBookLikes(int bookLikes) {
        this.bookLikes = bookLikes;
    }
}
