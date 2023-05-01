package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Doctor {
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private String address;
    private Integer loginid;
    private String department;
    private String certId;
    private String text;
    private Integer expert;
    /***/
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;
    private String image;
}