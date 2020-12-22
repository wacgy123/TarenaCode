package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -2671028987524519218L;
    private Integer id;
    private String name;
    private String note;
    private List<Integer> menuIds;
}
