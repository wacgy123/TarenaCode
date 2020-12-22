package com.cy.springboot.mybatis.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteByIdTest {
    @Autowired
    private GoodsDao goodsDao;
    @Test
    public void deleteById(){
        Integer id=1;
        goodsDao.deleteById(id);
    }
    @Test
    public void testDeleteObjects(){
        int rows=goodsDao.deleteObjects(3,4);
        System.out.println("delete.rows="+rows);
    }//通过日志级别控制日志的输出trace<debug<info<error
}
