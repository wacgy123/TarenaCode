package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -3390842818791655814L;
    private Integer id;
    private String name;
    private Integer parentId;
    private String url;
    private Integer type;
    private Integer sort;
    private String note;
    private String permission;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
