package com.cy.springboot.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
public class MyBatisTests {
    @Autowired
    private SqlSession sqlSession;
    @Test
    void testGetConnection(){
        Connection conn=sqlSession.getConnection();
        System.out.println("conn="+conn);
    }
}
