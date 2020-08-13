package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloWorld
 * @Description TODO
 * @Author ht
 * @Date 2020/8/12 16:45
 * @Version 1.0
 **/
  @Controller
  public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
      return "hello";
    }

    @GetMapping("/success")
    public  String  index(){
      return "index";
    }

  @GetMapping("/vip1")
  public  String  vip(){
    return  "vip";
  }
  }
