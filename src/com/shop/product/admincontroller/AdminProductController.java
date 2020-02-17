package com.shop.product.admincontroller;

import com.shop.categorysecond.service.CategorySecondService;
import com.shop.categorysecond.vo.CategorySecond;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台商品管理表现层对象
 */
@Controller
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategorySecondService categorySecondService;

    /**
     * 带分页的查询商品的方法
     */
    @RequestMapping("adminProduct_findAll")
    public String findAll(@RequestParam("page") Integer page, Map<String, Object> map) {
        // 调用 Service 完成查询操作
        PageBean<Product> pageBean = productService.findByPage(page);
        map.put("pageBean", pageBean);
        return "admin/product/list";
    }

    /**
     * 跳转到编辑页面的方法
     */
    @RequestMapping("adminProduct_addPage")
    public String edit(Map<String, Object> map) {
        // 查询所有二级分类的集合
        List<CategorySecond> csList = categorySecondService.findAll();
        map.put("csList", csList);
        return "admin/product/add";
    }

    /**
     * 保存商品的方法
     */
    @RequestMapping(value = "adminProduct_save", method = RequestMethod.POST)
    public String save(Product product) {
        // 补全商品参数
        product.setPdate(new Date());
        // 图片url
        product.setImage("");
        productService.save(product);
        return "redirect:adminProduct_findAll?page=1";
    }

    /**
     * 删除商品的方法
     */
    @RequestMapping("adminProduct_delete")
    public String delete(Product product) {
        // 先查询再删除
        product = productService.findByPid(product.getPid());
        // 删除上传的图片
        String path = product.getImage();
        if (path != null) {
            // 获取图片绝对路径
            File file = new File(path);
            file.delete();
        }
        // 删除商品
        productService.delete(product);
        return "redirect:adminProduct_findAll?page=1";
    }

    /**
     * 跳转到编辑商品的方法
     */
    @RequestMapping("adminProduct_edit")
    public String edit(Product product, Map<String, Object> map) {
        // 根据商品id查询该商品
        product = productService.findByPid(product.getPid());
        // 查询所有的二级分类的集合
        List<CategorySecond> csList = categorySecondService.findAll();
        map.put("product", product);
        map.put("csList", csList);
        return "admin/product/edit";
    }

    /**
     * 修改商品信息的方法
     */
    @RequestMapping(value = "adminProduct_update", method = RequestMethod.POST)
    public String update(Product product) {
        product.setPdate(new Date());
        // 文件上传
        // 先判断，如果不为空则上传，上传之前先删除原来的
        // 修改商品信息到数据库
        productService.update(product);
        return "redirect:adminProduct_findAll?page=1";
    }
}
