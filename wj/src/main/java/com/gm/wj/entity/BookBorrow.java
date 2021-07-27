package com.gm.wj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name="book_borrow")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@ToString
public class BookBorrow {
    @Id
    @Column(name = "borrowid")
    int borrowid;

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

    @Column(name = "status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BookBorrow(String username, String title, Date startdate, Date enddate, String status) {
        this.username = username;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public BookBorrow() {
    }

    public int getBorrowid() {
        return borrowid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBorrowid(int id) {
        this.borrowid = id;
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
