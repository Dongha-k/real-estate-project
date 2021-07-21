package com.project.real_estate_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
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
}
