package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;


@Getter
@Setter
public class ItemForm {

    private int id;
    private String name;
    private int price;
    private String image;
    private boolean status;
    private Date createDate;
    private Date updateDate;

}
