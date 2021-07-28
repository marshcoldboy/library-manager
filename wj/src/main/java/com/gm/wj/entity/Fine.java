/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_fine")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private int fid;

    /*罚款用户名*/
    @Column(name = "username")
    private String username;

    /*外键 指向BookBorrow表的主键*/
    @OneToOne
    @JoinColumn(name = "borrowid")
    private BookBorrow bookborrow;

    /*罚款数额*/
    @Column(name = "fine")
    private double fine;

    /*逾期归还天数*/
    @Column(name = "days")
    private int days;

    /*罚款缴纳状态*/
    @Transient
    private String status;

    public Fine( BookBorrow bookBorrow) {
        this.username=bookBorrow.getUsername();
        this.bookborrow = bookBorrow;
        int days=(int)((bookBorrow.getReturndate().getTime()-bookBorrow.getEnddate().getTime())/(1000*24*60*60));
        this.setDays(Math.max(0,days));
        this.fine=this.days*0.1;
    }

    public Fine() {
    }

}
