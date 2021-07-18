package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.MemberGetDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member/get")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/all")
    public List<Member> getAllMembers(){
        return memberService.findAllMember();
    }

    @PostMapping("/one")
    public ResponseEntity<Member> getMemberInfo(@Valid @RequestBody MemberGetDto memberGetDto){
        String userId = memberGetDto.getUserId();
        HttpHeaders httpHeaders = new HttpHeaders();
        if(userId.trim().isEmpty() || userId == null){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        Member findMember = null;
        try {
            findMember = memberService.findByUserId(userId);
            if (findMember == null) {
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code","98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        catch (Exception e){
            httpHeaders.add("code","99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(findMember, httpHeaders, HttpStatus.OK);
    }
}

