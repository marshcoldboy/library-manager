package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "book_return")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class BookReturn {

    @Id
    @Column(name = "returnid")
    private int returnid;

    @OneToOne
    @JoinColumn(name = "borrowid")
    private BookBorrow bookborrow;

    @Column(name = "returndate")
    private Date returndate;

    @Column(name = "deny")
    private Boolean deny;

    @Column(name = "fine")
    private double fine;

    public BookReturn(BookBorrow bookBorrow,Date returnDate)
    {
        this.bookborrow =bookBorrow;
        this.returndate=returnDate;
        this.deny=false;
        int days=(int)((returnDate.getTime()-bookBorrow.getEnddate().getTime())/(1000*24*60*60));
        this.fine=Math.max(0,days)*0.1;
    }

    public BookReturn() {

    }
}
