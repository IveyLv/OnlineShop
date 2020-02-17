package com.shop.categorysecond.vo;

import com.shop.category.vo.Category;
import com.shop.product.vo.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 二级分类实体类对象
 */
@Component
public class CategorySecond {
    private int csid;
    private String csname;

    // 所属一级分类，存的是一级分类的对象
    private Category category;

    // 配置商品集合
    private Set<Product> products = new HashSet<>();

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorySecond that = (CategorySecond) o;
        return csid == that.csid &&
                Objects.equals(csname, that.csname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csid, csname);
    }

    @Override
    public String toString() {
        return "CategorySecond{" +
                "csid=" + csid +
                ", csname='" + csname + '\'' +
                ", category=" + category +
                ", products=" + products +
                '}';
    }
}
