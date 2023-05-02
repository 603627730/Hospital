package com.hospital.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospital.entity.Medicalhistory;
import com.hospital.entity.Patient;
import com.hospital.service.MedicalhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yantonghui
 * @date 2023/5/2 10:52 AM
 */
@RestController
@RequestMapping("/api")
public class HistoryController {

    @Autowired
    MedicalhistoryService medicalhistoryService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    // 根据patientId获取症状信息
    @RequestMapping("/history/{id}")
    public R getSymptom(@PathVariable String id) {

        String s = redisTemplate.opsForValue().get("user");
        if (Objects.isNull(s)){
            s = "1";
        }
        id = s;
        // 根据patientId获取症状信息
        QueryWrapper<Medicalhistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patientid", id);
        List<Medicalhistory> list = medicalhistoryService.list(queryWrapper);
        // 转换成patient对象
        List<Patient> result = new ArrayList<>(list.size());
        for (Medicalhistory medicalhistory : list) {
            Patient patient = new Patient();
            patient.setId(medicalhistory.getId());
            patient.setName(medicalhistory.getName());
            result.add(patient);
        }
        return R.success(result);
    }

}
