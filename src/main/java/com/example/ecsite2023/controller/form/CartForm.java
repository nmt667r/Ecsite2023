package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class CartForm {
    private int id;
    private int itemId;
    private int userId;
    private int amount;
    private String name;
    private int price;
    private Date createDate;
    private Date updateDate;
}
