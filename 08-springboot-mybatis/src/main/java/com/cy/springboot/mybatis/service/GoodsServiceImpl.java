package com.cy.springboot.mybatis.service;

import com.cy.springboot.mybatis.dao.GoodsDao;
import com.cy.springboot.mybatis.pojo.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;
    private final Logger logger= LoggerFactory.getLogger(GoodsService.class);

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        long start=System.currentTimeMillis();
        logger.info("start {}",start);
        List<Goods> goodsList=goodsDao.getAllGoods();
        long end=System.currentTimeMillis();
        logger.info("end {}",end);
        logger.info("execute time {}",(end-start)+"ms");
        return goodsList;
    }

    @Override
    public void deleteById(Integer id) {
        long start=System.currentTimeMillis();
        logger.info("start {}",start);
        goodsDao.deleteById(id);
        long end=System.currentTimeMillis();
        logger.info("end {}",end);
        logger.info("execute time {}",(end-start)+"ms");
    }

    @Override
    public int deleteObjects(Integer... ids) {
        return 0;
    }
}
