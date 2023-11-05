package com.example.ecsite2023.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String account;

    @Column
    private String name;

    @Column
    private String password;

    @Column(name = "is_stopped")
    private boolean isStopped;

    @Column(updatable = false, name = "created_date")
    private Date createDate;

    @Column(name = "updated_date")
    private Date updateDate;
}
