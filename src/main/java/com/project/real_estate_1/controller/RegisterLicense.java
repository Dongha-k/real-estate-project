package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.CertRegisterDto;
import com.project.real_estate_1.dto.ResponseCode;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/register")
public class RegisterLicense {
    @Autowired
    private MemberService memberService;

    @PostMapping("{userId}")
    public ResponseCode registerCert(@PathVariable String userId,
        @Valid @RequestBody CertRegisterDto certRegisterDto) {
        System.out.println("공인중개사 자격 요청들어옴");
        System.out.println(certRegisterDto.toString());
        try {
            if (memberService.registerCertification(userId, certRegisterDto)) return new ResponseCode(0);
            else return new ResponseCode(1);
        } catch (SQLException e){
            return new ResponseCode(98);
        } catch (Exception e){
            return new ResponseCode(99);
        }
    }
}