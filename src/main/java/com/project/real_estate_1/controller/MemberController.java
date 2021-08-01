package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.dto.MemberGetDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/member/get")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private JoinService joinService;

    @RequestMapping(value = "/all", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<Member>> getAllMembers(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Member> members = null;
        try{
            members = memberService.findAllMember();
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(members, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/one")
    public ResponseEntity<Member> getMemberInfo(@ModelAttribute MemberGetDto memberGetDto){
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

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<List<BoardDto>> getMemberList(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<BoardDto> boardDtoList = null;
        try{
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            boardDtoList = memberService.getListOfMember(userId);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
    }
}