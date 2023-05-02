package com.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Medicalhistory {
    private Integer id;

    private Integer patientid;
    @TableField(exist = false)
    private String patientname;
    private String name;
    private Date time;
    private Integer hospitalizationid;
    private Integer doctorid;
    @TableField(exist = false)
    private String doctorname;
}