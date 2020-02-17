package com.shop.category.dao;

import com.shop.category.vo.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 一级分类持久层对象
 */
@Repository
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取与当前线程绑定的 Session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Dao 层查询所有一级分类
     */
    public List<Category> findAll() {
        String hql = "from Category";
        Query query = getSession().createQuery(hql);

        return query.list();
    }

    /**
     * 持久层后台保存一级分类的方法
     */
    public void save(Category category) {
        getSession().save(category);
    }

    /**
     * 持久层根据id查询一级分类的方法
     */
    public Category findByCid(int cid) {
        return (Category) getSession().get(Category.class, cid);
    }

    /**
     * 持久层删除一级分类的方法
     */
    public void delete(Category category) {
        getSession().delete(category);
    }

    /**
     * 持久层修改一级分类的方法
     */
    public void update(Category category) {
        getSession().update(category);
    }
}
