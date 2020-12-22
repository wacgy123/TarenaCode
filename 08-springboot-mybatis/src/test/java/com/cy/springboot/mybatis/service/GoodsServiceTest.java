package com.cy.springboot.mybatis.service;

import com.cy.springboot.mybatis.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;
    @Test
    public void getAllGoodsTest() throws SQLException {
        List<Goods> goodsList=goodsService.getAllGoods();
        goodsList.forEach(System.out::println);
    }
}
