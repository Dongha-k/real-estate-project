package com.project.real_estate_1.controller;

import com.project.real_estate_1.dto.OfferDto;
import com.project.real_estate_1.entity.Member;
import com.project.real_estate_1.entity.SalesOffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @RequestMapping("/setReturnTest")
    public Set<String> setHandler(){
        Set<String> set = new HashSet<>();
        set.add("e");
        set.add("a");
        set.add("b");
        set.add("e");
        return set;
    }
    @ResponseBody
    @PostMapping(value = "/multifile", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public Integer testHandler(@RequestParam(value="fileList") List<MultipartFile> fileList){
        System.out.println(fileList.size());
        return fileList.size();
    }
}
