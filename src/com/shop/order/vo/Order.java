package com.shop.order.vo;

import com.shop.user.vo.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {
    private Integer oid;
    private Double total;
    private Date orderTime;
    private Integer state;      // 1：未付款  2：已付款，但没有发货  3：已发货  4：交易完成
    private String name;
    private String phone;
    private String addr;

    // 订单所属用户
    private User user;
    // 订单中所属的多个订单项
    private Set<OrderItem> orderItems = new HashSet<>();

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order orders = (Order) o;
        return oid == orders.oid &&
                Objects.equals(total, orders.total) &&
                Objects.equals(orderTime, orders.orderTime) &&
                Objects.equals(state, orders.state) &&
                Objects.equals(name, orders.name) &&
                Objects.equals(phone, orders.phone) &&
                Objects.equals(addr, orders.addr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, total, orderTime, state, name, phone, addr);
    }
}
