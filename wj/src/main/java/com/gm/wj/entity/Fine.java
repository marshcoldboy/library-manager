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

    @OneToOne
    @JoinColumn(name = "borrowid")
    private BookBorrow bookBorrow;

    @Column(name = "fine")
    private double fine;

    public Fine( BookBorrow bookBorrow) {
        this.bookBorrow = bookBorrow;
    }

    public Fine() {
    }
}
