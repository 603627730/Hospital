package com.hospital.dao;

import com.hospital.entity.Seek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper

public interface SeekMapper {
    Integer insert(Seek seek);
    Integer updateDrugs(Seek seek);
    Seek getSeekByPatientId(@Param("patientid")Integer patientid);
}
