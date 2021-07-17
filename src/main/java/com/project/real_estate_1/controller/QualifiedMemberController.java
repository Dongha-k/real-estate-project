package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qualified/get")
public class QualifiedMemberController {
    @Autowired
    private MemberService memberService;
    @GetMapping("/")
    public List<Member> getAllQualifiedMembers(){
        return memberService.findAllQualifiedMembers();
    }
}