package com.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.hospital.dao")
public class HospitalApp {
    public static void main(String[] args){
        SpringApplication.run(HospitalApp.class,args);
    }

}
