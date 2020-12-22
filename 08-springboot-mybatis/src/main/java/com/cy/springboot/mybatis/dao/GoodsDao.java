package com.cy.springboot.mybatis.dao;

import com.cy.springboot.mybatis.pojo.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GoodsDao {
    public List<Goods> getAllGoods() throws SQLException;
    @Delete("delete from tb_goods where id=#{id}")
    public void deleteById(Integer id);
    int deleteObjects(Integer... ids);
}
