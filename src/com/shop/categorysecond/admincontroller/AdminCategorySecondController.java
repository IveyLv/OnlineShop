package com.shop.categorysecond.admincontroller;

import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.categorysecond.service.CategorySecondService;
import com.shop.categorysecond.vo.CategorySecond;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 后台二级分类表现层对象
 */
@Controller
public class AdminCategorySecondController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategorySecondService categorySecondService;

    /**
     * 查询所有二级分类的方法
     */
    @RequestMapping("adminCategorySecond_findAll")
    public String findAll(@RequestParam("page") Integer page, Map<String, Object> map) {
        PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
        map.put("pageBean", pageBean);
        return "admin/categorysecond/list";
    }

    /**
     * 跳转到二级分类添加页面
     */
    @RequestMapping("adminCategorySecond_addPage")
    public String addPage(Map<String, Object> map) {
        // 查询所有一级分类
        List<Category> cList = categoryService.findAll();
        map.put("cList", cList);
        return "admin/categorysecond/add";
    }

    /**
     * 保存二级分类的方法
     */
    @RequestMapping("adminCategorySecond_save")
    public String save(CategorySecond categorySecond) {
        categorySecondService.save(categorySecond);
        return "redirect:adminCategorySecond_findAll?page=1";
    }

    /**
     * 删除二级分类的方法
     */
    @RequestMapping("adminCategorySecond_delete")
    public String delete(CategorySecond categorySecond) {
        // 如果级联删除，先查询，再删除，需要配置 cascade
        System.out.println("con before" + categorySecond);
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        System.out.println("con after" + categorySecond);
        categorySecondService.delete(categorySecond);
        return "redirect:adminCategorySecond_findAll?page=1";
    }

    /**
     * 跳转到编辑二级分类的方法
     */
    @RequestMapping("adminCategorySecond_edit")
    public String edit(CategorySecond categorySecond, Map<String, Object> map) {
        // 根据二级分类id查询二级分类对象
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        // 查询所有的一级分类
        List<Category> cList = categoryService.findAll();
        map.put("categorySecond", categorySecond);
        map.put("cList", cList);
        return "admin/categorysecond/edit";
    }

    /**
     * 编辑二级分类的方法
     */
    @RequestMapping(value = "adminCategorySecond_update", method = RequestMethod.POST)
    public String update(CategorySecond categorySecond) {
        categorySecondService.update(categorySecond);
        /*System.out.println(categorySecond);
        System.out.println(categorySecond.getCategory().getCid());*/
        return "redirect:adminCategorySecond_findAll?page=1";
    }
}
