package com.shop.order.admincontroller;

import com.shop.order.service.OrderService;
import com.shop.order.vo.Order;
import com.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 后台订单管理表现层对象
 */
@Controller
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("adminOrder_findAll")
    public String findAll(@RequestParam("page") Integer page, Map<String, Object> map) {
        PageBean<Order> pageBean = orderService.findByPage(page);
        map.put("pageBean", pageBean);
        return "admin/order/list";
    }

    /**
     * 根据订单id查询订单项
     */
    public String findOrderItem(Order order, Map<String, Object> map) {
        List<Order> oList = orderService.findOrderItem(order.getOid());
        map.put("list", oList);
        return "admin/order/orderItem";
    }
}
