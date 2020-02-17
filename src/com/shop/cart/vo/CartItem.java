package com.shop.cart.vo;

import com.shop.product.vo.Product;

/**
 * 购物项对象
 */
public class CartItem {

    private Product product;        // 购物项中商品信息
    private int count;              // 购买某种商品数量
    private double subtotal;        // 购买某种商品小计

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        // 自动计算商品小计
        subtotal = count * product.getShopPrice();
        return subtotal;
    }

    /*public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }*/
}
