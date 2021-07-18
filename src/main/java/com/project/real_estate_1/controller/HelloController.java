package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("helloworld")
    @ResponseBody
    public ResponseEntity<Member> getMember(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("code", "test!");
        return new ResponseEntity<>(new Member(), httpHeaders, HttpStatus.OK);
    }
}