package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.entity.SalesOfferURL;
import com.project.real_estate_1.service.offer_service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("write")
public class WriterController {
    // 매물 올리는 글 작성

    @Autowired
    private WriteService writeService;

    @PostMapping("/board")
    public ResponseEntity<SalesOffer> writeHandler(@ModelAttribute OfferDto offerDto,
                                                   @RequestPart List<MultipartFile> fileList){
        System.out.println("글쓰기 요청됨");
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;

        return new ResponseEntity<>(salesOffer, httpHeaders, HttpStatus.OK);
    }
}