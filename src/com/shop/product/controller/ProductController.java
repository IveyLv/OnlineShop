package com.shop.product.controller;

import com.shop.category.service.CategoryService;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 商品控制层代码
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据商品id查询商品信息（查询商品详情）
     */
    @RequestMapping("product_findByPid")
    public ModelAndView findByPid(@RequestParam("pid") Integer pid) {
        Product product = productService.findByPid(pid);

        String viewName = "product";
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    /**
     * 根据分类id查询商品
     */
    @RequestMapping("product_findByCid")
    public String findByCid(@RequestParam("cid") Integer cid, @RequestParam("page") Integer page,
                            Map<String, Object> map) {
        // 一级分类信息在首页加载时，已经被查询，并且将数据信息放置到了 Session 中（cList）
        // 因此，页面跳转后直接从 Session 中获取信息即可，
        // 无需再次调用 List<Category> cList = categoryService.findAll(); 查询
        PageBean<Product> pageBean = productService.findByPageCid(cid, page);    // 根据一级分类查询商品，带分页查询
        // 将数据存入到 request 中
        map.put("pageBean", pageBean);
        return "productList";
    }

    /**
     * 根据二级分类id查询商品
     */
    @RequestMapping("product_findByCsid")
    public String findByCsid(@RequestParam("csid") Integer csid, @RequestParam("page") Integer page,
                             Map<String, Object> map) {
        PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
        // 将数据存入到 request 中
        map.put("pageBean", pageBean);
        return "productList";
    }
}
