package com.project.real_estate_1.controller.deal;


import com.project.real_estate_1.dto.MemberShortInfo;
import com.project.real_estate_1.dto.OfferStateForDeal;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.offer_service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @PostMapping("/findMemberByPhoneNum")
    public ResponseEntity<MemberShortInfo> findMemberByPhoneNumHandler(@RequestParam String phonenumber){
        String phoneNumber = phonenumber;
        HttpHeaders httpHeaders = new HttpHeaders();
        MemberShortInfo memberShortInfo = null;
        try{
            memberShortInfo = memberService.findByPhoneNum(phoneNumber);
            System.out.println(memberShortInfo.toString());
            if(memberShortInfo == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);

        } catch (Exception e){
            httpHeaders.add("code", "99");
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }

        httpHeaders.add("code", "00");
        return new ResponseEntity<>(memberShortInfo, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/getSalesOfferForDeal")
    public ResponseEntity<OfferStateForDeal> getSalesOfferForDealHandler(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        OfferStateForDeal offerStateForDeal = null;
        try{
            offerStateForDeal = boardService.getOfferStateForDealByIdx(idx);
            if(offerStateForDeal == null) {
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(offerStateForDeal, httpHeaders, HttpStatus.OK);
    }


}
