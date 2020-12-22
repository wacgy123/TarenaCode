package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserMenu implements Serializable {
    private static final long serialVersionUID = -4382305140177002905L;
    private Integer id;
    private String name;
    private String url;
    private List<SysUserMenu> childs;
}
