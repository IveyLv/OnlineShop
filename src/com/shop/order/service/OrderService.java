package com.shop.order.service;

import com.shop.order.dao.OrderDao;
import com.shop.order.vo.Order;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单模块业务层对象
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 保存订单的方法
     */
    public void save(Order order) {
        orderDao.save(order);
    }

    /**
     * 我的订单的业务层方法
     */
    public PageBean<Order> findByPageUid(Integer uid, Integer page) {
        PageBean<Order> pageBean = new PageBean<>();
        // 设置当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 5;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = 0;
        totalCount = orderDao.findByCountUid(uid);
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
        List<Order> list = orderDao.findByPageUid(uid, begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 业务层根据订单id查询订单的方法
     */
    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    /**
     * 业务层修改订单的方法
     */
    public void update(Order currOrder) {
        orderDao.update(currOrder);
    }

    /**
     * 业务层分页查询所有订单的方法
     */
    public PageBean<Order> findByPage(Integer page) {
        PageBean<Order> pageBean = new PageBean<>();
        // 设置当前的页数
        pageBean.setPage(page);

        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);

        // 设置总记录数
        int totalCount = 0;
        totalCount = orderDao.findByCount();
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
        List<Order> list = orderDao.findByPage(begin, limit);
        pageBean.setList(list);

        return pageBean;
    }

    /**
     * 业务层根据订单id查询订单项
     */
    public List<Order> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }
}
