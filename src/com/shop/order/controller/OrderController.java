package com.shop.order.controller;

import com.shop.cart.vo.Cart;
import com.shop.cart.vo.CartItem;
import com.shop.order.service.OrderService;
import com.shop.order.vo.Order;
import com.shop.order.vo.OrderItem;
import com.shop.user.vo.User;
import com.shop.utils.PageBean;
import com.shop.utils.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 订单模块控制层对象
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 生成订单的方法
     */
    @RequestMapping("order_save")
    public String save(Map<String, Object> map) {
        // 1. 保存数据到数据库中
        Order order = new Order();
        // 订单数据补全
        order.setOrderTime(new Date());
        order.setState(1);      // 1：未付款  2：已付款，但没有发货  3：已发货  4：交易完成
        // 总计的数据是购物车中的信息
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            map.put("orderMsg", "亲！您还没有购物！请先去购物！");
            return "index";
        }
        order.setTotal(cart.getTotal());
        // 设置订单中的购物项
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }
        // 设置订单所属的用户
        User existUser = (User) request.getSession().getAttribute("existUser");
        if (existUser == null) {
            map.put("loginMsg", "亲！你还没有登录！请先登录！");
            return "login";
        }
        order.setUser(existUser);

        orderService.save(order);
        // 2. 将订单对象显示到页面上
        map.put("order", order);
        // 清空购物车
        cart.clearCart();
        return "order";
    }

    /**
     * 我的订单的查询
     */
    @RequestMapping("order_findByUid")
    public String findByUid(@RequestParam("page") Integer page, Map<String, Object> map) {
        // 根据用户id查询
        User existUser = (User) request.getSession().getAttribute("existUser");
        // 调用 Service
        PageBean<Order> pageBean = orderService.findByPageUid(existUser.getUid(), page);
        // 将分页数据存入到 Request 域中
        map.put("pageBean", pageBean);
        return "orderList";
    }

    /**
     * 根据订单的id查询订单
     */
    @RequestMapping("order_findByOid")
    public String findByOid(@RequestParam("oid") Integer oid, Map<String, Object> map) {
        Order order = orderService.findByOid(oid);
        map.put("order", order);
        return "order";
    }

    /**
     * 为订单付款的方法
     */
    @RequestMapping(value = "order_payOrder", method = RequestMethod.POST)
    public void payOrder(Order order, @RequestParam("pd_FrpId") String pd_FrpId, HttpServletResponse response) throws IOException {
        // 修改订单
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());
        currOrder.setAddr(order.getAddr());
        orderService.update(currOrder);

        String p0_Cmd = "Buy";     // 业务类型
        String p1_MerId = "10001126856";      // 商户编号
        String p2_Order = order.getOid().toString();       // 商户订单号
        String p3_Amt = "0.01";     // 支付金额
        String p4_Cur = "CNY";     // 交易币种
        String p5_Pid = "";     // 商品名称
        String p6_Pcat = "";        // 商品种类
        String p7_Pdesc = "";       // 商品描述
        String p8_Url = "http://localhost:8080/shop/order_callBack";     // 商户接收支付成功数据的地址
        String p9_SAF = "";     // 送货地址
        String pa_MP = "";      // 商户扩展信息
        // pd_FrpId;       //支付通道编码
        String pr_NeedResponse = "1";        // 应答机制
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid,
                p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);       // 签名数据

        // 向易宝出发
        StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
        stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
        stringBuffer.append("p2_Order=").append(p2_Order).append("&");
        stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
        stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
        stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
        stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
        stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        stringBuffer.append("p8_Url=").append(p8_Url).append("&");
        stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
        stringBuffer.append("pa_MP=").append(pa_MP).append("&");
        stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
        stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        stringBuffer.append("hmac=").append(hmac);

        // 重定向到易宝
        response.sendRedirect(stringBuffer.toString());
    }

    /**
     * 付款成功后的转向
     */
    public String callBack(@RequestParam("r6_Order") String r6_Order, Map<String, Object> map) {
        // 修改订单的状态：修改状态为已经付款
        Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
        currOrder.setState(2);
        orderService.update(currOrder);
        // 在页面显示付款成功的信息
        map.put("msg", "订单付款成功：订单编号：" + r6_Order);
        return "msg";
    }
}
