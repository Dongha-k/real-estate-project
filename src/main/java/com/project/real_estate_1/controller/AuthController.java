package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.offer_service.BoardService;
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
public class AuthController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/reliable")
    public ResponseEntity<SalesOffer> authorization(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        try{
            salesOffer = boardService.authOffer(idx);
            if(salesOffer == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(salesOffer, httpHeaders, HttpStatus.OK);
    }
}