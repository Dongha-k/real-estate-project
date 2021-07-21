package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.entity.SalesOfferURL;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.offer_service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/write")
public class WriterController {
    // 매물 올리는 글 작성

    @Autowired
    private WriteService writeService;
    @Autowired
    private JoinService joinService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/board")
    public ResponseEntity<SalesOffer> writeHandler(@ModelAttribute OfferDto offerDto,
                                                   @RequestPart List<MultipartFile> fileList){
        System.out.println("글쓰기 요청됨");
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        String userId = offerDto.getUserId(); // 유저 아이디를 받음


        // id 존재 유무 파악
        if(userId.trim().isEmpty() || userId == null) {
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        Member findMember = null;
        try {
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            else{
                findMember = memberService.findByUserId(userId);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch(Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        // id 존재 유무 파악 끝




        httpHeaders.add("code", "00");
        return new ResponseEntity<>(salesOffer, httpHeaders, HttpStatus.OK);
    }
}