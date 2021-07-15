package com.gm.wj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="book_borrow")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ToString
public class BookBorrow {
    @Id
    @Column(name = "borrow_id")
    int borrow_id;

    @Column(name="username")
    String username;

    @Column(name="title")
    String title;

    @Column(name="startdate")
    Date startdate;

    @Column(name="enddate")
    Date enddate;

    @Column(name="returndate")
    Date returndate;

    @Transient
    int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BookBorrow(int borrow_id, String username, String title, Date startdate, Date enddate, Date returndate) {
        this.borrow_id = borrow_id;
        this.username = username;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.returndate = returndate;
    }

    public BookBorrow() {
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBorrow_id(int id) {
        this.borrow_id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startDate) {
        this.startdate = startDate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date endDate) {
        this.enddate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String uid) {
        this.username = uid;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }
}
