package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.dto.ContractListDto;
import com.project.real_estate_1.dto.MemberGetDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.service.member.JoinService;
import com.project.real_estate_1.service.member.MemberService;
import com.project.real_estate_1.service.offer_service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/member/get")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private JoinService joinService;
    @Autowired
    private ContractService contractService;


    @RequestMapping(value = "/all", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<Member>> getAllMembers(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<Member> members = null;
        try{
            members = memberService.findAllMember();
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(members, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/one")
    public ResponseEntity<Member> getMemberInfo(@ModelAttribute MemberGetDto memberGetDto){
        String userId = memberGetDto.getUserId();
        HttpHeaders httpHeaders = new HttpHeaders();
        if(userId.trim().isEmpty() || userId == null){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        Member findMember = null;
        try {
            findMember = memberService.findByUserId(userId);
            if (findMember == null) {
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code","98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        catch (Exception e){
            httpHeaders.add("code","99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(findMember, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<List<BoardDto>> getMemberList(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<BoardDto> boardDtoList = null;
        if(userId == null || userId.isEmpty()){
            httpHeaders.add("code", "01");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        try{
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            boardDtoList = memberService.getListOfMember(userId);
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/intermediaryList")
    public ResponseEntity<List<ContractListDto>> getIntermediaryList(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ContractListDto> contractListDtos = null;
        try{
            if(userId == null || userId.trim().isEmpty()){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            contractListDtos = contractService.getIntermediaryList(userId);
        } catch(SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractListDtos, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/buyingList")
    public ResponseEntity<List<ContractListDto>> getBuyingList(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ContractListDto> contractListDtos = null;
        try{
            if(userId == null || userId.trim().isEmpty()){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            contractListDtos = contractService.getBuyingList(userId);
        } catch(SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractListDtos, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/sellingList")
    public ResponseEntity<List<ContractListDto>> getSellingList(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ContractListDto> contractListDtos = null;
        try{
            if(userId == null || userId.trim().isEmpty()){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            contractListDtos = contractService.getSellingList(userId);
        } catch(SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractListDtos, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/allContract")
    public ResponseEntity<List<ContractListDto>> getAllContract(@RequestParam String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<ContractListDto> contractListDtos = null;
        try{
            if(userId == null || userId.trim().isEmpty()){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            if(!joinService.findUser(userId)){
                httpHeaders.add("code", "02");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
            contractListDtos = contractService.getAllList(userId);
        } catch(SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(contractListDtos, httpHeaders, HttpStatus.OK);
    }
}