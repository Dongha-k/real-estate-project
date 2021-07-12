package com.project.real_estate_1.controller;

import com.project.real_estate_1.JoinService;
import com.project.real_estate_1.domain.JoinForm;
import com.project.real_estate_1.domain.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    private JoinService joinService;

    @RequestMapping("login")
    public String Login(){
        return "member/login";
    }
    @RequestMapping("join")
    public String join(){
        return "member/join";
    }

    @RequestMapping("joinSuccess")
    @ResponseBody
    public JoinForm JoinRequest(@RequestParam(value = "id") String id,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "passwordConfirm") String confirmPass){
        System.out.println("회원가입 요청됨");
        JoinForm joinForm = new JoinForm(id, password, confirmPass);
        System.out.println(joinForm.toString());


        joinService.joinUser(joinForm);

        return joinForm;
    }
    @RequestMapping("loginSuccess")
    @ResponseBody
    public LoginForm LoginRequest(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "password") String password){
        System.out.println("로그인 요청됨");
        LoginForm loginForm = new LoginForm(id, password);
        System.out.println(loginForm.toString());
        return loginForm;
    }
}