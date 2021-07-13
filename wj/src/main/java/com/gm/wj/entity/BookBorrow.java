package com.gm.wj.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="book_borrow")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class BookBorrow {
    @Id
    @Column(name = "bid")
    int bid;

    static public final SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");

    @Column(name="startDate")
    Date startDate;

    @Column(name="endDate")
    Date endDate;

    @Column(name="uid")
    int uid;

    public BookBorrow(int bid, Date startDate, Date endDate, int uid) {
        this.bid = bid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.uid = uid;
    }

    public BookBorrow() {

    }

    public int getBid() {
        return bid;
    }

    public void setBid(int id) {
        this.bid = id;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "BookBorrow{" +
                "bid=" + bid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", uid=" + uid +
                '}';
    }
}
