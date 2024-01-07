package com.example.ecsite2023.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "items")
public class Item {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String image;

    @Column
    private boolean status;

    @Column(updatable = false, name = "created_date")
    private Date createDate;

    @Column(name = "updated_date")
    private Date updateDate;
}
