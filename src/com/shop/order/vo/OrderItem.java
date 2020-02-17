package com.shop.order.vo;

import com.shop.product.vo.Product;

import java.util.Objects;

public class OrderItem {
    private int itemid;
    private Integer count;
    private Double subtotal;

    private Product product;
    private Order order;

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderitem = (OrderItem) o;
        return itemid == orderitem.itemid &&
                Objects.equals(count, orderitem.count) &&
                Objects.equals(subtotal, orderitem.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemid, count, subtotal);
    }
}
