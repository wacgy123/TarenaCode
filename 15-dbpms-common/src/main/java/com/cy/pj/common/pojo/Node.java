package com.cy.pj.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = 485127885402380257L;
    private Integer id;
    private String name;
    private Integer parentId;
}
