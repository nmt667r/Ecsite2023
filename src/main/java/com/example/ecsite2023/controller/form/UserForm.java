package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class UserForm extends FormValidationMessage.UserForm{
    private Integer id;

    @NotBlank(message = ACCOUNT_BLANK)
    @Max(value = ACCOUNT_MAX_LENGTH, message= ACCOUNT_LENGTH_OVER)
    @Min(value = ACCOUNT_MIN_LENGTH, message= ACCOUNT_LENGTH_UNDER)
    private String account;

    @NotBlank(message = NAME_BLANK)
    @Max(value = NAME_MAX_LENGTH, message = NAME_LENGTH_OVER)
    @Min(value = NAME_MIN_LENGTH, message= NAME_LENGTH_UNDER)
    private String name;

    @NotBlank(message = PASSWORD_BLANK)
    @Max(value = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_OVER)
    @Min(value = PASSWORD_MIN_LENGTH, message= PASSWORD_LENGTH_UNDER)
    private String password;

    @NotBlank(message = CONFIRM_PASSWORD_BLANK)
    @Max(value = CONFIRM_PASSWORD_MAX_LENGTH, message = CONFIRM_PASSWORD_LENGTH_OVER)
    @Min(value = CONFIRM_PASSWORD_MIN_LENGTH, message= CONFIRM_PASSWORD_LENGTH_UNDER)
    private String passwordConfirm;

    private Integer is_stopped;
    private Date createDate;
    private Date updateDate;
}
