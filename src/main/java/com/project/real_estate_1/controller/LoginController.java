package com.project.real_estate_1.controller;

import com.project.real_estate_1.domain.JoinForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    // login test
    @RequestMapping("login")
    public String Login(){
        return "member/join";
    }

    @RequestMapping(value = "joinSuccess")
    @ResponseBody
    public JoinForm JoinSuccess(@RequestParam(value = "id") String id,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "passwordConfirm") String confirmPass){
        System.out.println("회원가입 요청됨");
        JoinForm joinForm = new JoinForm(id, password, confirmPass);
        System.out.println(joinForm.toString());
        return joinForm;
    }
}