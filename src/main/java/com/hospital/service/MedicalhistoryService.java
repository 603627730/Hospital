package com.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hospital.entity.Medicalhistory;

import java.util.List;

public interface MedicalhistoryService extends IService<Medicalhistory> {
    List<Medicalhistory> getAllMedicalhistorys(String doctorname,String patientname);
    String delMedicalhistory(Integer id);
    Medicalhistory getMedicalhistory(Integer id);
    String UpdateMedicalhistory(Medicalhistory medicalhistory);
    String addMedicalhistory(Medicalhistory medicalhistory);
    List<Medicalhistory> getMedicalhistoryByPatientId(Integer id);
}
