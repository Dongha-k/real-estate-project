package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.offer_service.WriteService;
import com.project.real_estate_1.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private StorageService storageService;

    public WriterController(StorageService storageService){
        this.storageService = storageService;
    }


    @PostMapping(value = "/board", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> writeHandler(@ModelAttribute OfferDto offerDto,
                                                   @RequestPart(value="fileList") List<MultipartFile> fileList){
        System.out.println("글쓰기 요청됨");
        System.out.println(offerDto.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        String userId = offerDto.getUserId(); // 유저 아이디를 받음


        // id 존재 유무 파악
        if(userId.trim().isEmpty() || userId == null) {
            httpHeaders.add("code", "01");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        Member findMember = null;
        try {
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            else{
                findMember = memberService.findByUserId(userId);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch(Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        // id 존재 유무 파악 끝

        List<String> urls = new ArrayList<>();
        String imgUrl = "";
        for(MultipartFile file : fileList){
            String fileName = storageService.store(file);
            Path path = storageService.load(fileName);
            imgUrl = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", path.getFileName().toString()).build().toUri().toString();
            urls.add(imgUrl);
        }


        try{
            salesOffer = writeService.write(offerDto, urls);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            System.out.println(e.getMessage());
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }




    @PostMapping(value = "/board2")
    public ResponseEntity<String> writeHandler2(@ModelAttribute OfferDto offerDto,
                                                @RequestPart(value = "file1", required = false) MultipartFile file1,
                                                @RequestPart(value = "file2", required = false) MultipartFile file2,
                                                @RequestPart(value = "file3", required = false) MultipartFile file3,
                                                @RequestPart(value = "file4", required = false) MultipartFile file4,
                                                @RequestPart(value = "file5", required = false) MultipartFile file5,
                                                @RequestPart(value = "file6", required = false) MultipartFile file6,
                                                @RequestPart(value = "file7", required = false) MultipartFile file7,
                                                @RequestPart(value = "file8", required = false) MultipartFile file8,
                                                @RequestPart(value = "file9", required = false) MultipartFile file9


                                                ){
        System.out.println("글쓰기(2) 요청됨");
        System.out.println(offerDto.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        String userId = offerDto.getUserId(); // 유저 아이디를 받음


        // id 존재 유무 파악
        if(userId==null || userId.trim().isEmpty()) {
            httpHeaders.add("code", "01");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        Member findMember = null;
        try {
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            else{
                findMember = memberService.findByUserId(userId);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch(Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        // id 존재 유무 파악 끝




        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        fileList.add(file5);
        fileList.add(file6);
        fileList.add(file7);
        fileList.add(file8);
        fileList.add(file9);

        List<String> urls = new ArrayList<>();
        String imgUrl = "";
        for(MultipartFile file : fileList){
            if(file == null) continue;
            System.out.println("사진 저장 ... ");
            String fileName = storageService.store(file);
            Path path = storageService.load(fileName);
            imgUrl = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", path.getFileName().toString()).build().toUri().toString();
            urls.add(imgUrl);
        }

        try{
            salesOffer = writeService.write(offerDto, urls);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            System.out.println(e.getMessage());
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }
}