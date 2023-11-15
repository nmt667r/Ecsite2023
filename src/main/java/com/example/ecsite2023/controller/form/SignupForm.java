package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class SignupForm {
    private String account;
    private String name;
    private String password;
    private String passwordConfirm;
    private Integer is_stopped;
    private Date createDate;
    private Date updateDate;
}
