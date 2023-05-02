package com.hospital.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hospital.controller.front.utils.ValidateCodeUtils;
import com.hospital.entity.Patient;
import com.hospital.service.CategoryService;
import com.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    /**
     * 发送手机短信验证码
     *
     * @param patient
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody Patient patient, HttpSession session) {
        //获取手机号
        String phone = patient.getPhone();

        if (StringUtils.isNotBlank(phone)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);

            //调用阿里云提供的短信服务API完成发送短信
//            SMSUtils.sendMessage("瑞吉外卖","",phone,code);

            //需要将生成的验证码保存到Session
            session.setAttribute(phone, code);

            return R.success("手机验证码短信发送成功");
        }

        return R.error("短信发送失败");
    }


    @Autowired
    private PatientService patientService;

    @Autowired
    CategoryService categoryService;

    /**
     * 注册
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<Patient> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());
        String phone = null;
        //获取手机号
        if (Objects.nonNull(map.get("phone"))) {
            phone = map.get("phone").toString();
        }

        //获取验证码
        String code = map.get("code").toString();

        String certId = null;
        if (Objects.nonNull(map.get("certId"))) {
            certId = map.get("certId").toString();
        }
        String age = null;
        if (Objects.nonNull(map.get("age"))) {
            age = map.get("age").toString();
        }
        String sex = null;
        if (Objects.nonNull(map.get("sex"))) {
            sex = map.get("sex").toString();
        }
        String address = null;
        if (Objects.nonNull(map.get("address"))) {
            address = map.get("address").toString();
        }
        String name = null;
        if (Objects.nonNull(map.get("name"))) {
            name = map.get("name").toString();
        }

        //从Session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInSession != null && codeInSession.equals(code)) {

            if (Objects.isNull(age)) {
                LambdaQueryWrapper<Patient> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Patient::getPhone, phone);
                Patient patient = patientService.getOne(queryWrapper);
                if (Objects.isNull(patient)) {
                    return R.error("请先注册");
                }
                session.setAttribute("user", patient.getId());
                return R.success(patient);
            }

            //如果能够比对成功，说明登录成功

            LambdaQueryWrapper<Patient> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Patient::getPhone, phone);
            Patient patient = patientService.getOne(queryWrapper);
            if (patient == null) {
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                patient = new Patient();
                patient.setPhone(phone);
                patient.setCertId(certId);
                patient.setAge(Integer.parseInt(age));
                patient.setSex(Integer.valueOf(sex));
                patient.setAddress(address);
                patient.setName(name);
                patient.setStatus(1);
                patientService.save(patient);
            }
            // 根据手机号获取用户信息
            session.setAttribute("user", patient.getId());
            return R.success(patient);
        }
        return R.error("登录失败");
    }

    @GetMapping("/t")
    public String test() {
        System.out.println("---------");
        List<Category> list = categoryService.list(new LambdaQueryWrapper<>());

        return list.toString();
    }

}
