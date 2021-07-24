package com.project.real_estate_1.controller.adminToDo;

import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthGetListController {

    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/getReady", method = {RequestMethod.GET, RequestMethod.POST})

    public ResponseEntity<List<Member>> getReadyMembers(){
        List<Member> members;
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            members = memberService.getReadyMember();
        } catch (SQLException e){
            httpHeaders.add("code","98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

        } catch (Exception e){
            httpHeaders.add("code","99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(members, httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/getQualified", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<Member>> getQualifiedMembers(){
        List<Member> members;
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            members = memberService.getQualifiedMember();
        } catch (SQLException e){
            httpHeaders.add("code","98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

        } catch (Exception e){
            httpHeaders.add("code","99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(members, httpHeaders, HttpStatus.OK);
    }

}
