package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.BoardDto;
import com.project.real_estate_1.entity.SalesOffer;
import com.project.real_estate_1.service.offer_service.BoardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/board/get")
public class BoardController {
    @Autowired
    public BoardService boardService;

    @PostMapping("/OneByIdx")
    public ResponseEntity<SalesOffer> getContentByIdx(@RequestParam Long idx){
        HttpHeaders httpHeaders = new HttpHeaders();
        SalesOffer salesOffer = null;
        System.out.println(idx + "번 매물 글에 대한 정보를 요청함");
        try {
            salesOffer = boardService.findContentByIdx(idx);
            if (salesOffer == null){
                httpHeaders.add("code", "01");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
            }
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "99");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        System.out.println(salesOffer.toString());
        return new ResponseEntity<>(salesOffer, httpHeaders, HttpStatus.OK);
    }



    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<List<BoardDto>> getContentList(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<BoardDto> boardDtoList = null;
        try{
            boardDtoList = boardService.getListOfOffer();
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/uncheckedList", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<List<BoardDto>> getUncheckedContentList(){
        HttpHeaders httpHeaders = new HttpHeaders();
        List<BoardDto> boardDtoList = null;
        try{
            boardDtoList = boardService.getUncheckedListOfOffer();
        } catch (SQLException e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e){
            httpHeaders.add("code", "98");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        }
        httpHeaders.add("code", "00");
        return new ResponseEntity<>(boardDtoList, httpHeaders, HttpStatus.OK);
    }


}