package com.shop.product.dao;

import com.shop.product.vo.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品持久层代码
 */
@Repository
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取与当前线程绑定的 Session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 首页热门商品查询
     */
    public List<Product> findHot() {
        // 1. 创建一个 Criteria 对象
        Criteria criteria = getSession().createCriteria(Product.class);

        // 2. 添加查询条件: 在QBC中查询条件使用Criterion来表示
        // Criterion可以通过Restrictions的静态方法得到
        criteria.add(Restrictions.eq("isHot", 1));
        criteria.addOrder(Order.desc("pdate"));

        // 3. 执行查询
        List list = criteria.setFirstResult(0)
                .setMaxResults(10)
                .list();
        return list;
    }

    /**
     * 首页最新商品查询
     */
    public List<Product> findNew() {
        Criteria criteria = getSession().createCriteria(Product.class);

        criteria.addOrder(Order.desc("pdate"));

        List list = criteria.setFirstResult(0)
                .setMaxResults(10)
                .list();
        return list;
    }

    /**
     * 根据商品ID查询商品信息
     */
    public Product findByPid(Integer pid) {
        return (Product) getSession().get(Product.class, pid);
    }

    /**
     * 根据分类id查询商品个数
     */
    public int findCountCid(Integer cid) {
        String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, cid);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 根据分类id查询商品集合
     */
    public List<Product> findByPageCid(Integer cid, int begin, int limit) {
        // select p.* from category c, categorysecond cs, product p where c.cid = cs.cid and cs.csid p.csid and c.cid = ?
        // select p from Category c, CategorySecond cs, Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
        String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, cid)
                .setFirstResult(begin)
                .setMaxResults(limit);
        List<Product> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 根据二级分类查询商品个数
     */
    public int findCountCsid(Integer csid) {
        String hql = "select count(*) from Product p where p.categorySecond.csid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, csid);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 根据二级分类查询商品集合
     */
    public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
        String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, csid)
                .setFirstResult(begin)
                .setMaxResults(limit);
        List<Product> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 持久层统计商品个数的方法
     */
    public int findCount() {
        String hql = "select count(*) from Product";
        Query query = getSession().createQuery(hql);

        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 持久层查询所有商品信息的方法
     */
    public List<Product> findByPage(int begin, int limit) {
        String hql = "from Product order by pdate desc";
        Query query = getSession().createQuery(hql);

        query.setFirstResult(begin)
                .setMaxResults(limit);
        List<Product> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 持久层保存商品的方法
     */
    public void save(Product product) {
        getSession().save(product);
    }

    /**
     * 持久层删除商品的方法
     */
    public void delete(Product product) {
        getSession().delete(product);
    }

    /**
     * 持久层修改商品的方法
     */
    public void update(Product product) {
        getSession().update(product);
    }
}
