package com.shop.product.service;

import com.shop.product.dao.ProductDao;
import com.shop.product.vo.Product;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品业务层代码
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 首页热门商品查询
     */
    public List<Product> findHot() {
        return productDao.findHot();
    }

    /**
     * 首页最新商品查询
     */
    public List<Product> findNew() {
        return productDao.findNew();
    }

    /**
     * 查询指定商品信息
     */
    public Product findByPid(Integer pid) {
        return productDao.findByPid(pid);
    }

    /**
     * 根据一级分类的cid带有分页的查询
     */
    public PageBean<Product> findByPageCid(Integer cid, Integer page) {
        PageBean<Product> pageBean = new PageBean<>();
        // 当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 8;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = 0;
        totalCount = productDao.findCountCid(cid);
        pageBean.setTotalCount(totalCount);

        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        // 每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> list = productDao.findByPageCid(cid, begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 根据二级分类查询商品信息
     */
    public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
        PageBean<Product> pageBean = new PageBean<>();
        // 当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 8;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = 0;
        totalCount = productDao.findCountCsid(csid);
        pageBean.setTotalCount(totalCount);

        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        // 每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> list = productDao.findByPageCsid(csid, begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 业务层查询商品带分页的方法
     */
    public PageBean<Product> findByPage(Integer page) {
        PageBean<Product> pageBean = new PageBean<>();
        // 当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = productDao.findCount();
        pageBean.setTotalCount(totalCount);

        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        // 每页显示的数据集合
        int begin = (page - 1) * limit;
        List<Product> list = productDao.findByPage(begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 业务层保存商品的方法
     */
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * 业务层删除商品的方法
     */
    public void delete(Product product) {
        productDao.delete(product);
    }

    /**
     * 业务层修改商品的方法
     */
    public void update(Product product) {
        productDao.update(product);
    }
}
