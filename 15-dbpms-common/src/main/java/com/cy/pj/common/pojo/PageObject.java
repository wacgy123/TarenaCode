package com.cy.pj.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageObject<T> implements Serializable {
    private static final long serialVersionUID = -5307436796659944757L;
    private Integer rowCount;
    private List<T> records;
    private Integer pageCount;
    private Integer pageSize;
    private Integer pageCurrent;

    public PageObject(Integer rowCount, List<T> records, Integer pageSize, Integer pageCurrent) {
        this.rowCount = rowCount;
        this.records = records;
        this.pageSize = pageSize;
        this.pageCurrent = pageCurrent;
        this.pageCount=rowCount / pageSize;
        if(rowCount%pageSize!=0){
            this.pageCount++;
        }
    }
}
