package com.project.real_estate_1.controller;

import com.project.real_estate_1.controller.util.ServerUtil;
import com.project.real_estate_1.dto.LoginDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.dto.JoinDto;
import com.project.real_estate_1.dto.ResponseCode;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.storage.StorageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.nio.file.Path;
import java.sql.SQLException;
@Controller
public class LoginController {

    @Value("${defaultProfileURL}")
    String defaultProfileURL;



    @Autowired
    private JoinService joinService;
    @Autowired
    private MemberService memberService;

    private StorageService storageService;

    public LoginController(StorageService storageService){
        this.storageService = storageService;
    }
    @RequestMapping("register")
    public String Register(){
        return "member/register";
    }
    @RequestMapping("login")
    public String Login(){
        return "member/login";
    }

    @RequestMapping("join")
    public String join(){
        return "member/join";
    }

    @RequestMapping("join2")
    public String join2(){return "member/join_revised";}

    @ApiOperation(value = "회원가입", notes = "회원가입하는 컨트롤러")
    @PostMapping (value = "/joinRequest")
    @ResponseBody
    public ResponseEntity<Member> JoinRequest(@ModelAttribute JoinDto joinDto,
                                              @RequestPart(required = false) MultipartFile file){
        System.out.println("회원가입 요청됨");
        System.out.println(joinDto.toString());
        String id = joinDto.getUserId();
        String password = joinDto.getPassword();
        String confirmPass = joinDto.getPasswordConfirm();
        HttpHeaders httpHeaders = new HttpHeaders();

        String imgUrl = "";
        if(file != null && !file.isEmpty()) {
            String fileName = storageService.store(file);
            Path path = storageService.load(fileName);
            imgUrl = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                    "serveFile", path.getFileName().toString()).build().toUri().toString();
        }
        else{
            imgUrl = defaultProfileURL;
        }
        if(id.trim().isEmpty() || id == null) {
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(password.trim().isEmpty() || password == null) {
            httpHeaders.add("code", "02");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(confirmPass.trim().isEmpty() || password == null) {
            httpHeaders.add("code", "03");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        try{
            if(joinService.findUser(id)) {
                httpHeaders.add("code", "04");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(!password.equals(confirmPass)) {
            httpHeaders.add("code", "05");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(!joinService.checkId(id)) {
            httpHeaders.add("code", "06");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(!joinService.checkPass(password)) {
            httpHeaders.add("code", "07");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        String phoneNumber = joinDto.getPhoneNumber();
        String name = joinDto.getName();
        String nickname = joinDto.getNickname();
        if(phoneNumber.trim().isEmpty()) {
            httpHeaders.add("code", "08");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(name.trim().isEmpty()) {
            httpHeaders.add("code", "09");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(nickname.trim().isEmpty()) {
            httpHeaders.add("code", "10");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(!joinService.checkPhoneNumber(phoneNumber)) {
            httpHeaders.add("code", "11");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(!joinService.checkIdNum(joinDto.getIdNum())){
            httpHeaders.add("coce", "12");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        Member newer;
        try{
            newer = joinService.joinUser(joinDto, imgUrl);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(newer, httpHeaders, HttpStatus.OK);
    }



    @PostMapping("/loginRequest")
    @ResponseBody
    public ResponseEntity<Member> LoginRequest(@ModelAttribute LoginDto loginDto){
        System.out.println("로그인 요청됨");
        System.out.println(loginDto.toString());
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        HttpHeaders httpHeaders = new HttpHeaders();
        if(id.trim().isEmpty() || id == null) {
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        if(password.trim().isEmpty() || password == null) {
            httpHeaders.add("code", "02");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        Member existingMember;
        try{
            if(!joinService.findUser(id)){
                httpHeaders.add("code", "03");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!joinService.findPass(id, password)) {
                httpHeaders.add("code", "04");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            existingMember = memberService.findByUserId(id);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch(Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(existingMember, httpHeaders, HttpStatus.OK);
    }
}