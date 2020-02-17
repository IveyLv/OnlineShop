package com.shop.order.dao;

import com.shop.order.vo.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单模块持久层对象
 */
@Repository
public class OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 保存订单的方法
     */
    public void save(Order order) {
        getSession().save(order);
    }

    /**
     * 我的订单个数的统计
     */
    public int findByCountUid(Integer uid) {
        String hql = "select count(*) from Order o where o.user.uid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, uid);
        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 我的订单的查询
     */
    public List<Order> findByPageUid(Integer uid, int begin, int limit) {
        String hql = "from Order o where o.user.uid=? order by ordertime desc";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, uid)
                .setFirstResult(begin)
                .setMaxResults(limit);
        List<Order> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 根据订单id查询订单
     */
    public Order findByOid(Integer oid) {
        return (Order) getSession().get(Order.class, oid);
    }

    /**
     * 修改订单的方法
     */
    public void update(Order currOrder) {
        getSession().update(currOrder);
    }

    /**
     * 持久层查询订单个数的方法
     */
    public int findByCount() {
        String hql = "select count(*) from Order";
        Query query = getSession().createQuery(hql);

        List<Long> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 持久层分页查询所有订单
     */
    public List<Order> findByPage(int begin, int limit) {
        String hql = "from Order o order by ordertime desc";
        Query query = getSession().createQuery(hql);

        query.setFirstResult(begin)
                .setMaxResults(limit);
        List<Order> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    /**
     * 持久层根据订单id查询订单项
     */
    public List<Order> findOrderItem(Integer oid) {
        String hql = "from OrderItem oi where oi.order.oid=?";
        Query query = getSession().createQuery(hql);

        query.setInteger(0, oid);
        List<Order> list = query.list();
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
}
