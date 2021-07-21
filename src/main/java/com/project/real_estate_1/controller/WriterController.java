package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.entity.SalesOfferURL;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("write")
public class WriterController {
    @PostMapping("/board")
    public ResponseEntity<SalesOffer> writeHandler(){
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        return new ResponseEntity<>(salesOffer, httpHeaders, HttpStatus.OK);
    }
}