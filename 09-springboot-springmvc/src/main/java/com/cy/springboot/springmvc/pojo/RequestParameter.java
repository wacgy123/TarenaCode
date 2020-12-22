package com.cy.springboot.springmvc.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class RequestParameter {
    private String name;
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
}
