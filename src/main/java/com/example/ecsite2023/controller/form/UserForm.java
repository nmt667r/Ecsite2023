package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserForm {

    private int id;
    private String account;
    private String name;
    private String password;
    private boolean isStopped;
    private Date createDate;
    private Date updateDate;
}