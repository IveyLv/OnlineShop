package com.shop.cart.controller;

import com.shop.cart.vo.Cart;
import com.shop.cart.vo.CartItem;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车控制层对象
 */
@Controller
public class CartController {

    @Autowired
    private ProductService productService;      // 注入商品的 Service

    @Autowired
    private HttpServletRequest request;

    /**
     * 将购物项添加够购物车
     */
    @RequestMapping(value = "cart_addCart", method = RequestMethod.POST)
    public String addCart(@RequestParam("pid") Integer pid, @RequestParam("count") Integer count) {
        // 封装一个 CartItem 对线
        CartItem cartItem = new CartItem();
        // 设置数量
        cartItem.setCount(count);
        // 根据 pid 查询商品
        Product product = productService.findByPid(pid);
        // 设置商品
        cartItem.setProduct(product);

        // 将购物项添加到购物车
        // 购物车应该存在 session 中
        Cart cart = getCart();
        cart.addCart(cartItem);

        return "cart";
    }

    /**
     * 清空购物车
     */
    @RequestMapping("cart_clearCart")
    public String clearCart() {
        // 获得购物车对象
        Cart cart = getCart();
        // 调用购物车中的清空方法
        cart.clearCart();
        return "cart";
    }

    /**
     * 从购物车中移除购物项
     */
    @RequestMapping("cart_removeCart")
    public String removeCart(@RequestParam("pid") Integer pid) {
        // 获得购物车对象
        Cart cart = getCart();
        // 调用购物车中的移除方法
        cart.removeCart(pid);
        return "cart";
    }

    /**
     * 跳转到我的购物车
     */
    @RequestMapping("cart_myCart")
    public String myCart() {
        return "cart";
    }

    // 获得购物车方法：从 session 中获得购物车
    private Cart getCart() {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }
}
