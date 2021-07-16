package com.project.real_estate_1.controller;

import com.project.real_estate_1.vo.ResponseCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterLicense {
    @PostMapping("{userId}")
    public ResponseCode registerCert(@PathVariable String userId){
        return new ResponseCode(9);
    }
}