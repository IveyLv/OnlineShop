package com.shop.categorysecond.dao;

import com.shop.categorysecond.vo.CategorySecond;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 二级分类持久层对象
 */
@Repository
public class CategorySecondDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 持久层统计二级分类个数的方法
     */
    public int findCount() {
        String hql = "select count(*) from CategorySecond";
        Query query = getSession().createQuery(hql);

        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 持久层分页查询二级分类的方法
     */
    public List<CategorySecond> findByPage(int begin, int limit) {
        String hql = "from CategorySecond order by csid desc";
        Query query = getSession().createQuery(hql);

        query.setFirstResult(begin)
                .setMaxResults(limit);
        List<CategorySecond> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 持久层保存二级分类的方法
     */
    public void save(CategorySecond categorySecond) {
        getSession().save(categorySecond);
    }

    /**
     * 持久层根据id查询二级分类的方法
     */
    public CategorySecond findByCsid(int csid) {
        return (CategorySecond) getSession().get(CategorySecond.class, csid);
    }

    /**
     * 持久层删除二级分类的方法
     */
    public void delete(CategorySecond categorySecond) {
        System.out.println("dao:" + categorySecond);
        getSession().delete(categorySecond);
    }

    /**
     * 持久层修改二级分类的方法
     */
    public void update(CategorySecond categorySecond) {
        getSession().update(categorySecond);
    }

    /**
     * 持久层查询所有二级分类的方法
     */
    public List<CategorySecond> findAll() {
        String hql = "from CategorySecond";
        Query query = getSession().createQuery(hql);

        return query.list();
    }
}
