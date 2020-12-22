package com.cy.springboot.mybatis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteById {
    @Autowired
    private GoodsService goodsService;
    @Test
    public void deleteById(){
        Integer id=2;
        goodsService.deleteById(id);
    }

}
