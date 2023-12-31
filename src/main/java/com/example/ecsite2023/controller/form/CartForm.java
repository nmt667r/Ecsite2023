package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
public class CartForm {
    private Integer id;
    private Integer itemId;
    private Integer userId;
    private Integer amount;
    private String name;
    private Integer price;
    private Date createDate;
    private Date updateDate;
}
