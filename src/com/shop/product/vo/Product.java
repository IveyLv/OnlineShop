package com.shop.product.vo;

import com.shop.categorysecond.vo.CategorySecond;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 商品实体类对象
 */
@Component
public class Product {
    private int pid;
    private String pname;
    private Double marketPrice;
    private Double shopPrice;
    private String image;
    private String pdesc;
    private Integer isHot;
    private Date pdate;

    // 二级分类的外键：使用二级分类的对象
    private CategorySecond categorySecond;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public CategorySecond getCategorySecond() {
        return categorySecond;
    }

    public void setCategorySecond(CategorySecond categorySecond) {
        this.categorySecond = categorySecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pid == product.pid &&
                Objects.equals(pname, product.pname) &&
                Objects.equals(marketPrice, product.marketPrice) &&
                Objects.equals(shopPrice, product.shopPrice) &&
                Objects.equals(image, product.image) &&
                Objects.equals(pdesc, product.pdesc) &&
                Objects.equals(isHot, product.isHot) &&
                Objects.equals(pdate, product.pdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, pname, marketPrice, shopPrice, image, pdesc, isHot, pdate);
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", marketPrice=" + marketPrice +
                ", shopPrice=" + shopPrice +
                ", image='" + image + '\'' +
                ", pdesc='" + pdesc + '\'' +
                ", isHot=" + isHot +
                ", pdate=" + pdate +
                '}';
    }
}
