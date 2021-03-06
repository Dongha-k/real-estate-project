package com.project.real_estate_1.controller;

import com.project.real_estate_1.entity.Contract;
import com.project.real_estate_1.service.offer_service.BoardService;
import com.project.real_estate_1.service.offer_service.ContractService;
import org.apache.coyote.Response;
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
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    public BoardService boardService;
    @Autowired
    public ContractService contractService;

    @PostMapping("/board") // 게시글 삭제
    public ResponseEntity<String> deleteOffer(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            if(!boardService.deleteOffer(idx)){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
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

    @PostMapping("/contract")
    public ResponseEntity<String> deleteContract(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        try{
            if(!contractService.deleteContract(idx)){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>("failed", httpHeaders, HttpStatus.OK);
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