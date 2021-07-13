package com.project.real_estate_1.controller;

import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.domain.JoinForm;
import com.project.real_estate_1.domain.LoginForm;
import com.project.real_estate_1.vo.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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

    @RequestMapping("joinRequest")
    @ResponseBody
    public ResponseCode JoinRequest(@RequestParam(value = "id") String id,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "passwordConfirm") String confirmPass){
        System.out.println("회원가입 요청됨");
        JoinForm joinForm = new JoinForm(id, password, confirmPass);
        System.out.println(joinForm.toString());

        if(id.trim().isEmpty() || id == null) return new ResponseCode(1);
        if(password.trim().isEmpty() || password == null) return new ResponseCode(2);
        if(confirmPass.trim().isEmpty() || password == null) return new ResponseCode(3);
        try{
            if(joinService.findUser(id)) return new ResponseCode(4);
        } catch (SQLException e){
            return new ResponseCode(98);
        }
        if(!password.equals(confirmPass)) return new ResponseCode(5);
        if(!joinService.checkId(id)) return new ResponseCode(6);
        if(!joinService.checkPass(password)) return new ResponseCode(7);
        try{
            joinService.joinUser(joinForm);
        } catch (SQLException e){
            return new ResponseCode(98);
        } catch (Exception e){
            return new ResponseCode(99);
        }
        return new ResponseCode(0);
    }

    @RequestMapping("loginRequest")
    @ResponseBody
    public ResponseCode LoginRequest(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "password") String password){
        System.out.println("로그인 요청됨");
        LoginForm loginForm = new LoginForm(id, password);
        System.out.println(loginForm.toString());
        if(id.trim().isEmpty() || id == null) return new ResponseCode(1);
        if(password.trim().isEmpty() || password == null) return new ResponseCode(2);
        try{
            if(!joinService.findUser(id)) return new ResponseCode(3);
            if(!joinService.findPass(id, password)) return new ResponseCode(4);
        } catch (SQLException e){
            return new ResponseCode(98);
        } catch(Exception e){
            return new ResponseCode(99);
        }
        return new ResponseCode(0);
    }
}