package com.cy.pj.arithmetic.controller;

import com.cy.pj.common.pojo.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ArithmeticController {

    @RequestMapping("/doCompute/{n1}/{n2}")
    public ResponseResult doCompute(@PathVariable Integer n1,@PathVariable Integer n2){
        //if(n2==0)return new ResponseResult("除数不能为0");
        Integer result=n1/n2;
        return new ResponseResult("result is "+result);
    }
}
