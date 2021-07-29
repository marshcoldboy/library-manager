/** @author SYJ  */
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

    /*借阅者名字*/
    @Column(name="username")
    String username;

    /*借阅书籍名字*/
    @Column(name="title")
    String title;

    /*书籍开始借阅日期*/
    @Column(name="startdate")
    Date startdate;

    /*书籍规定归还日期*/
    @Column(name="enddate")
    Date enddate;

    /*书籍实际归还日求*/
    @Column(name="returndate")
    Date returndate;

    /*剩余归还天数*/
    @Transient
    int days;

    /*书籍借阅状态*/
    @Column(name = "status")
    String status;

    /*是否续借*/
    @Transient
    String renew;
}
