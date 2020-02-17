package com.shop.index.controller;

import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * 首页控制层代码
 */
@Controller
@SessionAttributes(value = {"cList"})
public class IndexController {

    @Autowired
    private CategoryService categoryService;    // 注入一级分类的 Service

    @Autowired
    private ProductService productService;    // 注入商品的 Service

    @RequestMapping("index_page")
    public String index(Map<String, Object> map) {
        // 查询所有一级分类
        List<Category> cList = categoryService.findAll();
        // 将一级分类存入到 Session 中
        map.put("cList", cList);

        // 查询热门商品
        List<Product> hList = productService.findHot();
        // 将热门商品信息保存到 Request 域中
        map.put("hList", hList);

        // 查询最新商品
        List<Product> nList = productService.findNew();
        // 将最新商品信息保存到 Request 域中
        map.put("nList", nList);
        return "index";
    }
}
