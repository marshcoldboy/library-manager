package com.gm.wj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

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

    static public final SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");

    public BookBorrow(int bid, String title, Date startDate, Date endDate, String uid) {
        this.borrow_id = bid;
        this.title=title;
        this.startdate = startDate;
        this.enddate = endDate;
        this.username = uid;
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
}
