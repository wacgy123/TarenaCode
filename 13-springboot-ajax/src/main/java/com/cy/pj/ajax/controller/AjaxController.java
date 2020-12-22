package com.cy.pj.ajax.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AjaxController {
    private List<Map<String, Object>> list = new ArrayList<>();

    public AjaxController() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 100);
        map.put("name", "huawei");
        map.put("remark", "very good");
        list.add(map);
    }

    @RequestMapping("/doAjaxStart")
    public String doAjaxStart() {
        return "Response Result Of Ajax Get Request 01 ";
    }

    @GetMapping("/doAjaxGet")
    public List<Map<String, Object>> doAjaxGet() {
        return list;
    }

    @PostMapping("/doAjaxPost")
    public String doAjaxPost(@RequestParam Map<String, Object> map) {
        list.add(map);
        return "save ok";
    }

    @DeleteMapping("/doAjaxDelete/{id}")
    public String doAjaxDelete(@PathVariable Integer id) {
        Iterator<Map<String,Object>> iter=list.iterator();
        while (iter.hasNext()){
            Map<String,Object> map=iter.next();
            if (((Integer) map.get("id")).equals(id)){
                iter.remove();
            }
        }
        return "delete ok";
    }

    @PutMapping("/doAjaxPut")
    public String doAjaxPut(@RequestParam Map<String,Object>map){
        String id=(String) map.get("id");
        Iterator<Map<String,Object>> iter=list.iterator();
        while (iter.hasNext()) {
            Map<String, Object> oldMap = iter.next();
            if(id.equals(String.valueOf(oldMap.get("id")))){
                oldMap.put("name",map.get("name"));
                oldMap.put("remark",map.get("remark"));
                break;
            }
        }
        return "update ok";
    }
}
