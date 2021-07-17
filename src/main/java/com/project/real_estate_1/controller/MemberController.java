package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member/get")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public List<Member> getAllMembers(){
        return memberService.findAllMember();
    }

    @GetMapping("/{userId}")
    public Member getMemberInfo(@PathVariable String userId){
        return memberService.findByUserId(userId);
    }
}
