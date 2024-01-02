package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class UserForm{
    private Integer id;

    @Size(min = 4, max = 20, message= "アカウント名は4~20文字で入力してください")
    private String account;

    @Size(min = 2, max = 20, message= "名前は2~20文字で入力してください")
    private String name;

    @Size(min = 8, max = 20, message= "パスワードは8~20文字で入力してください")
    private String password;

    @Size(min = 8, max = 20, message= "確認用パスワードは8~20文字で入力してください")
    private String passwordConfirm;

    private Integer is_stopped;
    private Date createDate;
    private Date updateDate;
}
