package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/alter")
public class AlterController {

    @PostMapping("/board")
    public ResponseEntity<SalesOffer> alterSalesOffer(
            @RequestParam Long idx,
            @ModelAttribute OfferDto offerDto,
            @RequestPart(required = false) List<MultipartFile> fileList
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();


        // TODO


        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }

}
