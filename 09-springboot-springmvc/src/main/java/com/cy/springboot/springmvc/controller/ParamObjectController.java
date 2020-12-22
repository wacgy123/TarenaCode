package com.cy.springboot.springmvc.controller;

import com.cy.springboot.springmvc.pojo.RequestParameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class ParamObjectController {
    @RequestMapping("/doHandleRequestParam02")
    public String doHandleRequestParam02(String name, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(required = false) Date startDate){
        return "request params name="+name+"/startDate="+startDate;
    }
    @RequestMapping("/doHandleRequestParam03/{id}/{code}")
    public String doHandleRequestParam03(@PathVariable Integer id,@PathVariable Integer code){
        return "request params id="+id+"code="+code;
    }
    @RequestMapping("/doHandleRequestParam04/{name}/{id}/{startDate}")
    public String doHandleRequestParam04(RequestParameter pojo){
        return "request params pojo="+pojo.toString();
    }
    @RequestMapping("/doHandleRequestParam05/{name}/{startDate}")
    public String doHandleRequestParam05(@PathVariable Map<String,Object> map){
        return "request params map="+map.toString();
    }
}
