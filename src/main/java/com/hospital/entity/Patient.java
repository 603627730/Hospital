package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Patient {
    private Integer id;

    private String name;

    private Integer age;

    private Integer sex;

    private String address;

    private Integer hospitalizationid;

    private String drugsids;

    private Integer appointmentid;
    private String certId;
    private Integer loginid;
    @TableField(exist = false)
    private Integer isout;
    @TableField(exist = false)
    private String doctorname;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;

    private String phone;

    private Integer status;
}