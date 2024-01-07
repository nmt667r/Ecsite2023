package com.example.ecsite2023.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
public class ItemForm {

    private int id;
    @Size(min = 2, max = 20, message= "アイテム名は2~20文字で入力してください")
    private String name;
    @NotBlank(message = "金額を入力してください")
    @Pattern(regexp="^\\d{1,10}$", message="金額は1~10桁の数字で入力してください")
    private String price;
    private String image;
    private boolean status;
    private Date createDate;
    private Date updateDate;

}
