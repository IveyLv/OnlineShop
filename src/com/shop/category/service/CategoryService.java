package com.shop.category.service;

import com.shop.category.dao.CategoryDao;
import com.shop.category.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一级分类业务层对象
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 业务层查询所有一级分类
     */
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     * 业务层后台保存一级分类的方法
     */
    public void save(Category category) {
        categoryDao.save(category);
    }

    /**
     * 业务层根据id查询一级分类的方法
     */
    public Category findByCid(int cid) {
        return categoryDao.findByCid(cid);
    }

    /**
     * 业务层删除一级分类的方法
     */
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    /**
     * 业务层修改一级分类的方法
     */
    public void update(Category category) {
        categoryDao.update(category);
    }
}
