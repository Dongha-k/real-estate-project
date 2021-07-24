package com.project.real_estate_1.controller.adminToDo;


import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.member.authService.MemberAuthService;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/auth")
public class MemberAuthController {
    @Autowired
    public MemberService memberService;
    @Autowired
    public MemberAuthService memberAuthService;
    @Autowired
    public JoinService joinService;




    @PostMapping("/qualification")
    public ResponseEntity<Member> qualifyMember(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        Member findMember = null;
        if(userId == null || userId.trim().isEmpty()){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        try {
            findMember = memberService.findByUserId(userId);
            if(findMember == null){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!memberAuthService.authorizationMember(findMember.getId())){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            findMember = memberService.findByUserId(userId);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(findMember, httpHeaders, HttpStatus.OK);
    }







    @PostMapping("/rejection")
    public ResponseEntity<Member> rejectMember(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        Member findMember = null;
        if(userId == null || userId.trim().isEmpty()){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        try {
            findMember = memberService.findByUserId(userId);
            if(findMember == null){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!memberAuthService.rejectMember(findMember.getId())){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            findMember = memberService.findByUserId(userId);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(findMember, httpHeaders, HttpStatus.OK);
    }
}
