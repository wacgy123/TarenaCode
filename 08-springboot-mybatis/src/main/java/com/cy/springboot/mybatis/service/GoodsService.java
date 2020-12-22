package com.cy.springboot.mybatis.service;

import com.cy.springboot.mybatis.pojo.Goods;

import java.sql.SQLException;
import java.util.List;

public interface GoodsService {
    public List<Goods> getAllGoods() throws SQLException;
    public void deleteById(Integer id);
    public int deleteObjects(Integer... ids);
}
