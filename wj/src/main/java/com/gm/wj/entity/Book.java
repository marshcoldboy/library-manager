package com.gm.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    private String ISBN;

    private int price;

    /**
     * Abstract of the book.
     */
    private String abs;

    /**
     * The url of the book's cover.
     */
    private String cover;

    /**
     * Category id.
     */
    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;
}
