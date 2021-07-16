package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.LoginDto;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.dto.JoinDto;
import com.project.real_estate_1.dto.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/joinRequest")
    @ResponseBody
    public ResponseCode JoinRequest(@Valid @RequestBody JoinDto joinDto){
        System.out.println("회원가입 요청됨");
        System.out.println(joinDto.toString());
        String id = joinDto.getId();
        String password = joinDto.getPassword();
        String confirmPass = joinDto.getPasswordConfirm();
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
            joinService.joinUser(joinDto);
        } catch (SQLException e){
            return new ResponseCode(98);
        } catch (Exception e){
            return new ResponseCode(99);
        }
        return new ResponseCode(0);
    }

    @PostMapping("/loginRequest")
    @ResponseBody
    public ResponseCode LoginRequest(@Valid @RequestBody LoginDto loginDto){
        System.out.println("로그인 요청됨");
        System.out.println(loginDto.toString());
        String id = loginDto.getId();
        String password = loginDto.getPassword();
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