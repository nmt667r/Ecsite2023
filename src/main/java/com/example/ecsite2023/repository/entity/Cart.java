package com.example.ecsite2023.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "carts")
public class Cart {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "user_id")
    private int userId;

    @Column
    private int amount;

    @Column(updatable = false, name = "created_date")
    private Date createDate;

    @Column(name = "updated_date")
    private Date updateDate;
}
