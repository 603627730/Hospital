package com.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hospital.entity.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorService extends IService<Doctor> {
    List<Doctor> getAllDoctor();
    List<Doctor> getAllDoctor(String name,String certId);
    String delDoctor(Integer id);
    String addDoctor(Doctor doctor);
    Doctor getDoctor(Integer id);
    String upDoctor(Doctor doctor);
    Doctor getDoctorByLoginId(Integer loginid);
    List<Doctor> getDoctorByDepartment(String department);
    String seekInfo(Map map);
}
