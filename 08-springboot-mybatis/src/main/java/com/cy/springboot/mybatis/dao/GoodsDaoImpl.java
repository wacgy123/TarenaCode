package com.cy.springboot.mybatis.dao;

import com.cy.springboot.mybatis.pojo.Goods;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class GoodsDaoImpl implements GoodsDao{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public List<Goods> getAllGoods(){
        System.out.println("==========GoodsDaoImpl=========");
        String statement="com.cy.springboot.mybatis.dao.GoodsDao.getAllGoods";
        return sqlSession.selectList(statement);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public int deleteObjects(Integer... ids) {
        String statement="com.cy.springboot.mybatis.dao.GoodsDao.deleteObjects";
        return sqlSession.delete(statement,ids);
    }
}
