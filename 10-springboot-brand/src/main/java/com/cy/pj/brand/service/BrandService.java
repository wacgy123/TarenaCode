package com.cy.pj.brand.service;

import com.cy.pj.brand.pojo.Brand;

import java.util.List;

/**
 * 品牌模块业务逻辑接口，在业务对象中要实现：、
 * 1)核心业务 (对核心业务数据的操作)
 * 2)拓展业务 (日志记录，权限控制，缓存处理，。。。。)
 */
public interface BrandService {

      Brand findById(Integer id);

      int updateBrand(Brand brand);
      int saveBrand(Brand brand);
      /**
       * 基于id执行删除业务
       * @param id (品牌id-参数类型尽量使用对象类型，其长度尽量与表中设计一致)
       * @return 删除的行数 (业务层方法中的insert,update,delete操作其返回值类型，
       * 还经常会使用void类型)
       */
      int deleteById(Integer id);

      List<Brand> findBrands(String name);
}
