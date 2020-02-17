package com.shop.category.admincontroller;

import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 后台一级分类管理表现层
 */
@Controller
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 后台查询所有一级分类的方法
     */
    @RequestMapping("adminCategory_findAll")
    public String findAll(Map<String, Object> map) {
        // 查询所有一级分类
        List<Category> cList = categoryService.findAll();
        // 将数据存入 Request 中
        map.put("cList", cList);
        return "admin/category/list";
    }

    /**
     * 跳转到一级分类添加页面
     */
    @RequestMapping("adminCategory_add")
    public String add() {
        return "admin/category/add";
    }

    /**
     * 后台保存一级分类的方法
     */
    @RequestMapping(value = "adminCategory_save", method = RequestMethod.POST)
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:adminCategory_findAll";
    }

    /**
     * 后台删除一级分类的方法
     */
    @RequestMapping("adminCategory_delete")
    public String delete(Category category) {
        // 删除一级分类，同时删除二级分类。必须先根据id查询，再进行删除
        category = categoryService.findByCid(category.getCid());
        categoryService.delete(category);
        return "redirect:adminCategory_findAll";
    }

    /**
     * 跳转到后台编辑一级分类的方法
     */
    @RequestMapping("adminCategory_edit")
    public String edit(Category category, Map<String, Object> map) {
        // 根据一级分类的id查询一级分类
        category = categoryService.findByCid(category.getCid());
        map.put("category", category);
        // 页面跳转
        return "admin/category/edit";
    }

    /**
     * 后台修改一级分类的方法
     */
    @RequestMapping(value = "adminCategory_update", method = RequestMethod.POST)
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:adminCategory_findAll";
    }
}
