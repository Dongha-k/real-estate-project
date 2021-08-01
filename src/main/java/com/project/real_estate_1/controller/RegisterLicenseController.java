package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.CertRegisterDto;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.sql.SQLException;

@RestController
@RequestMapping("/register")
public class RegisterLicenseController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private JoinService joinService;

    private StorageService storageService;

    public RegisterLicenseController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/certification")
    public ResponseEntity<String> registerCert(@ModelAttribute CertRegisterDto certRegisterDto,
                                               @RequestPart(required = false) MultipartFile file) {
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("자격 등록 요청됨 : 요청 id -" + certRegisterDto.getUserId());
        String imgUrl = "";
        if(!file.isEmpty()) {
            String fileName = storageService.store(file);
            Path path = storageService.load(fileName);
            imgUrl = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", path.getFileName().toString()).build().toUri().toString();
        }
        else{
            // 자격증 사진이 무조건 들어가야함
            httpHeaders.add("code", "01");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }

        String userId = certRegisterDto.getUserId();
        String certNum = certRegisterDto.getCertificationNumber();
        if(userId.trim().isEmpty()){
            httpHeaders.add("code", "02");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        if(certNum.trim().isEmpty()) {
            httpHeaders.add("code", "03");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        try{
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "04");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            if(!memberService.registerCertification(certRegisterDto, imgUrl)){
                httpHeaders.add("code", "05");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
        } catch(SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }
}