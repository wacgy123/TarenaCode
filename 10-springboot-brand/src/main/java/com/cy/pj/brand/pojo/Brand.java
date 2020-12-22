package com.cy.pj.brand.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Brand {//品牌模块的POJO对象，基于此对象封装品牌信息
    private Integer id;
    private String name;
    private String remark;
    private Date createdTime;
}
