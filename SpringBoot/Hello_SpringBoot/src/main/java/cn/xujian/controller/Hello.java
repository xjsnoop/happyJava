package cn.xujian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("hello")
    public String hello01(){
        return "hello springboot";
    }
}
