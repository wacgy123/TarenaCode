package com.cy.springboot.mybatis.dao;

import com.cy.springboot.mybatis.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
public class TestDao {

    //@Qualifier("goodsDaoImpl")
    @Autowired
    private GoodsDao goodsDao;
    @Test
    public void getAllGoodsTest() throws SQLException {
        List<Goods> goodsList=goodsDao.getAllGoods();
        for (Goods goods:goodsList) {
            System.out.println(goods.toString());
        }
    }
}
