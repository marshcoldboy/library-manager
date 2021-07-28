package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Book entity.
 *
 * @author Evan
 * @date 2019/4
 */
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

    /**
     * Title of the book.
     */
    private String title;

    /**
     * Author name.
     */
    private String author;

    /**
     * Press.
     */
    private String press;

    @Column(name="ISBN")
    private String isbn;

    @Column(name = "price")
    private double price;

    /**
     * Abstract of the book.
     */
    @Column(name = "abs")
    private String abs;

    /**
     * The url of the book's cover.
     */
    @Column(name = "cover")
    private String cover;

    @Column(name = "amount")
    private int amount;

    /**
     * Category id.
     */
    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;

    @Transient
    private int[] bids;
}
