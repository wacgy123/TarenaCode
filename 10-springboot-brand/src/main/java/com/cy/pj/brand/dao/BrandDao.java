package com.cy.pj.brand.dao;

import com.cy.pj.brand.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BrandDao {
    /**
     * 基于品牌id查询品牌信息
     * @param id
     * @return 查询到的品牌对象
     */
    @Select("select * from tb_brand where id=#{id}")
    Brand findById(Integer id);

    /**
     * 将brand对象更新到数据库
     */
    @Update("update tb_brand set name=#{name},remark=#{remark} where id=#{id}")
    int updateBrand(Brand brand);

    /**将brand对象持久化到数据库
     * 如何理解持久化？(将内存中的数据写入到数据库，可以持久保存)
     */
    @Insert("insert into tb_brand (name,remark,createdTime) values (#{name},#{remark},now())")
    int insertBrand(Brand brand);

    /**
     * 基于品牌id执行删除业务
     * @param id
     * @return
     * 说明：实际项目中，现在很多项目不做真正删除了，只是修改记录的状态，
     * 让当前记录处于一种删除状态即可(一般表中会有一个字段，例如isDelete)。
     */
    @Delete("delete from tb_brand where id=#{id}")
    int deleteById(Integer id);

    /**
     * 基于品牌名字执行查询操作
     * @param name
     * @return
     * 基于name值的不同，可能需要如下两种sql实现
     * select * from tb_brand; (name没有值时，应该默认查询所有)
     * select * from tb_brand where name like concat("%",#{name},"%")
     * FAQ? 如何基于条件实现如上两种sql的定义呢？动态SQL
     */
   List<Brand> findBrands(String name);

}
