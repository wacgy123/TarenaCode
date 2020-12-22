package com.cy.pj.brand.controller;

import com.cy.pj.brand.pojo.Brand;
import com.cy.pj.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping("/brand/doAddUI")
    public String doAddUI(){
        return "brand/brand-add";//返回品牌添加页面
    }

    @RequestMapping("/brand/doFindById/{id}")
    public String doFindById(@PathVariable  Integer id,Model model){
        Brand brand=brandService.findById(id);
        model.addAttribute("brand",brand);
        return "brand/brand-update";
    }


    @RequestMapping("/brand/doSaveBrand")
    public String doSaveBrand(Brand brand,Model model){
        brandService.saveBrand(brand);
        List<Brand> list= brandService.findBrands(null);
        model.addAttribute("list", list);
        return "brand/brand";//viewname
    }

    @RequestMapping("/brand/doUpdateBrand")
    public String doUpdateBrand(Brand brand,Model model){
        brandService.updateBrand(brand);
        List<Brand> list= brandService.findBrands(null);
        model.addAttribute("list", list);
        return "brand/brand";//viewname
    }

    @RequestMapping("/brand/doDeleteById/{id}")
    public String doDeleteById(@PathVariable Integer id,Model model){
        brandService.deleteById(id);
        List<Brand> list= brandService.findBrands(null);
        model.addAttribute("list", list);
        return "brand/brand";//viewname
    }

    @RequestMapping({"/brand/doFindBrands/{name}","/brand/doFindBrands/"})
    public String doFindBrands(@PathVariable(required = false)  String name, Model model){
        List<Brand> list= brandService.findBrands(name);
        model.addAttribute("list", list);
        return "brand/brand";//viewname
    }

}
