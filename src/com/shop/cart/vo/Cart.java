package com.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 *
 * 购物车功能：
 * 1. 将购物项添加到购物车
 * 2. 从购物车移除购物项
 * 3. 清空购物车
 */
public class Cart {

    // 购物项集合：Map 的 Key 就是商品pid，value：购物项
    private Map<Integer, CartItem> map = new LinkedHashMap<>();
    // 购物总计
    private double total;

    // Cart 对象中有一个叫 cartItems 属性
    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    // 购物车中购物项数目
    public Integer getCartItemsSize() {
        return getCartItems().size();
    }

    public double getTotal() {
        return total;
    }

    /**
     * 将购物项添加到购物车
     */
    public void addCart(CartItem cartItem) {
        // 判断购物车中是否已经存在该购物项
        /**
         *   * 如果存在：
         *         * 数量增加
         *         * 总计 = 总计 + 购物项小计
         *   * 如果不存在：
         *         * 向 map 中添加购物项
         *         * 总计 = 总计 + 购物项小计
         */
        // 获得商品 pid
        Integer pid = cartItem.getProduct().getPid();
        // 判断购物车中是否已经存在该购物项
        if (map.containsKey(pid)) {
            // 存在
            CartItem _cartItem = map.get(pid);   // 获得购物车中原来的购物项
            _cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
        } else {
            // 不存在
            map.put(pid, cartItem);
        }
        total += cartItem.getSubtotal();
    }

    /**
     * 从购物车移除购物项
     */
    public void removeCart(Integer pid) {
        // 将购物项移除购物车
        CartItem cartItem = map.remove(pid);
        // 总计 = 总计 - 移除购物项小计
        total -= cartItem.getSubtotal();
    }

    /**
     * 清空购物车
     */
    public void clearCart() {
        // 将所有购物项清空
        map.clear();
        // 总计记为 0
        total = 0;
    }
}
