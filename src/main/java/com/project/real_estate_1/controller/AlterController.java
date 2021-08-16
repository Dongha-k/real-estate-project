package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.member.AlterService;
import com.project.real_estate_1.service.member.JoinService;
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
import java.util.List;

@RestController
@RequestMapping("/alter")
public class AlterController {
    @Autowired
    private JoinService joinService;
    @Autowired
    private AlterService alterService;
    @Autowired
    private StorageService storageService;

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

    @PostMapping("/memberInfo")
    public ResponseEntity<String> alterMemberInfoHandler(
            @RequestParam String userId,
            @RequestParam String alterType,
            @RequestParam(required = false) String nickName,
            @RequestParam(required = false) String phoneNum,
            @RequestParam(required = false) String existingPass,
            @RequestParam(required = false) String newPass,
            @RequestParam(required = false) String confirmPass
    ){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(userId == null || userId.isEmpty()){
            httpHeaders.add("code", "01"); // 유저아이디가 null 이거나 빈문자열임
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        if(alterType == null || alterType.isEmpty()){ // 수정타입이 null이거나 빈문자열임
            httpHeaders.add("code", "02");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        if(!(alterType.equals("nick") || alterType.equals("phone") || alterType.equals("pass"))){
            httpHeaders.add("code", "03"); // 수정 타입이 형식에 안맞음
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        try{
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "04"); // 해당 유저아이디를 가진 유저가 존재하지 않음
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            if(alterType.equals("nick")){
                if(nickName == null || nickName.isEmpty()){
                    httpHeaders.add("code", "05");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // nickname이 빈 문자열이거나 null임
                }
                if(!alterService.changeNick(userId, nickName)){
                    httpHeaders.add("code", "06");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 닉네임 변경에 실패함
                }
            } else if(alterType.equals("phone")){
                if(phoneNum == null || nickName.isEmpty()){
                    httpHeaders.add("code", "07");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 전화번호가 빈문자열이거나 null임
                }
                if(!joinService.checkPhoneNumber(phoneNum)){
                    httpHeaders.add("code", "08");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 전화번호 형식이 안맞음
                }
                if(!alterService.changePhone(userId, phoneNum)){
                    httpHeaders.add("code", "09");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 전화번호 변경 실패
                }

            } else if(alterType.equals("pass")){
                if(existingPass == null || newPass == null || confirmPass == null ||
                    existingPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()){
                    httpHeaders.add("code", "10");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 비밀번호 중 하나 이상이 null이거나 빈문자열임
                }
                if(!newPass.equals(confirmPass)){
                    httpHeaders.add("code", "11");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 새 비밀번호랑 새 비밀번호 확인이 서로 일치하지 않음
                }
                if(!joinService.findPass(userId, existingPass)){
                    httpHeaders.add("code", "12");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 기존 비밀번호가 일치하지 않음
                }
                if(!joinService.checkPass(newPass)){
                    httpHeaders.add("code", "13");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 새 비밀번호가 형식에 맞지 않음
                }
                if(!alterService.changePass(userId, newPass)){
                    httpHeaders.add("code", "14");
                    return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                    // 비밀번호 변경 실패
                }
            }
            else{
                httpHeaders.add("code", "03");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                // 수정타입이 맞지 않음
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>("success", httpHeaders, HttpStatus.OK);
    }
    @PostMapping("/memberImg")
    public ResponseEntity<String> alterMemberImgHandler(
            @RequestParam String userId,
            @RequestPart(required = false) MultipartFile file){
        HttpHeaders httpHeaders = new HttpHeaders();
        if(userId == null || userId.isEmpty()){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }

        String imgUrl = "";
        if(file != null && !file.isEmpty()) {
            String fileName = storageService.store(file);
            Path path = storageService.load(fileName);
            imgUrl = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", path.getFileName().toString()).build().toUri().toString();
        }
        else{
            // 자격증 사진이 무조건 들어가야함
            httpHeaders.add("code", "02");
            return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
        }
        try{
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
            }
            if(!alterService.changeImg(userId, imgUrl)){

                httpHeaders.add("code", "04");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
                // 이미지 변경 실패
            }
        } catch (SQLException e){

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
