package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.controller.front.common.R;
import com.hospital.entity.Medicalhistory;
import com.hospital.service.DoctorService;
import com.hospital.service.MedicalhistoryService;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
public class MedicalhistoryController {
    @Autowired
    PatientService patientService;
    @Autowired
    MedicalhistoryService medicalhistoryService;
    @Autowired
    DoctorService doctorService;

    @RequestMapping("/admin/medicalhistoryManage")
    public String medicalhistoryManage(HttpServletRequest request, @RequestParam(value = "doctorname", required = false) String doctorname, @RequestParam(value = "patientname", required = false) String patientname) {
        request.setAttribute("doctorname", doctorname);
        request.setAttribute("patientname", patientname);
        request.setAttribute("medicalhistorys", medicalhistoryService.getAllMedicalhistorys(doctorname, patientname));
        return "admin/medicalhistoryManage";
    }

    @RequestMapping("/admin/medicalhistoryAdd")
    public String medicalhistoryAddPage(HttpServletRequest request) {
        request.setAttribute("doctors", doctorService.getAllDoctor());
        request.setAttribute("patients", patientService.getAllPatients());
        return "admin/add/medicalhistoryadd";
    }

    @RequestMapping(value = "/admin/medicalhistory/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delmedicalhistory(@PathVariable Integer id) {
        JSONObject json = new JSONObject();
        json.put("message", medicalhistoryService.delMedicalhistory(id));
        return json;
    }

    @RequestMapping(value = "/admin/medicalhistory/{id}", method = RequestMethod.GET)
    public String medicalhistoryInfo(@PathVariable Integer id, HttpServletRequest request) {
        request.setAttribute("patients", patientService.getAllPatients());
        request.setAttribute("doctors", doctorService.getAllDoctor());
        request.setAttribute("medicalhistory", medicalhistoryService.getMedicalhistory(id));
        return "admin/info/medicalhistoryInfo";
    }

    @RequestMapping(value = "/admin/medicalhistory", method = RequestMethod.PUT)
    @ResponseBody
    public JSONObject medicalhistoryUpdate(@RequestBody Medicalhistory medicalhistory) {
        JSONObject json = new JSONObject();
        json.put("message", medicalhistoryService.UpdateMedicalhistory(medicalhistory));
        return json;
    }

    @RequestMapping(value = "/admin/medicalhistory", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject medicalhistoryAdd(@RequestBody Medicalhistory medicalhistory) {
        JSONObject json = new JSONObject();
        json.put("message", medicalhistoryService.addMedicalhistory(medicalhistory));
        return json;
    }

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    // save
    @RequestMapping(value = "/api/addhis", method = RequestMethod.POST)
    @ResponseBody
    public R addhis(@RequestBody String name) {
        Medicalhistory medicalhistory = new Medicalhistory();
        medicalhistory.setName(name);
        if (Objects.isNull(redisTemplate.opsForValue().get("user"))) {
            medicalhistory.setPatientid(1);
        } else {
            medicalhistory.setPatientid(Integer.valueOf(redisTemplate.opsForValue().get("user")));
        }
        String s = medicalhistoryService.addMedicalhistory(medicalhistory);
        return R.success(s);
    }
}
