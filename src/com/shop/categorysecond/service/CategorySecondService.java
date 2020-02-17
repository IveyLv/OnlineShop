package com.shop.categorysecond.service;

import com.shop.categorysecond.dao.CategorySecondDao;
import com.shop.categorysecond.vo.CategorySecond;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 二级分类业务层对象
 */
@Service
public class CategorySecondService {

    @Autowired
    private CategorySecondDao categorySecondDao;

    /**
     * 业务层带分页查询所有二级分类的方法
     */
    public PageBean<CategorySecond> findByPage(Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<>();
        // 当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = categorySecondDao.findCount();
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
        List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 业务层保存二级分类的方法
     */
    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    /**
     * 业务层根据id查询二级分类的方法
     */
    public CategorySecond findByCsid(int csid) {
        return categorySecondDao.findByCsid(csid);
    }

    /**
     * 业务层删除二级分类的方法
     */
    public void delete(CategorySecond categorySecond) {
        System.out.println("service:" + categorySecond);
        categorySecondDao.delete(categorySecond);
    }

    /**
     * 业务层编辑二级分类的方法
     */
    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    /**
     * 业务层查询所有二级分类的方法
     * @return
     */
    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
