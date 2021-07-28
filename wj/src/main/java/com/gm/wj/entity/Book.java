/** @author SYJ  */
package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    private int bid;

    /*书名*/
    private String title;

    /*作者*/
    private String author;

    /*出版商*/
    private String press;

    /*ISBN*/
    @Column(name="ISBN")
    private String isbn;

    /*价格*/
    @Column(name = "price")
    private double price;

    /*图书简介*/
    @Column(name = "abs")
    private String abs;

    /*书籍封面图片url*/
    @Column(name = "cover")
    private String cover;

    /*图书数量*/
    @Column(name = "amount")
    private int amount;

    /*书籍类别*/
    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    /*记录多本书主键*/
    @Transient
    private int[] bids;
}
