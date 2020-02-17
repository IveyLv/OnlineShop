package com.shop.category.vo;

import com.shop.categorysecond.vo.CategorySecond;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 一级分类实体类对象
 */
@Component
public class Category {
    private int cid;
    private String cname;

    // 一级分类中存放二级分类的集合
    private Set<CategorySecond> categorySeconds = new HashSet<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Set<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return cid == category.cid &&
                Objects.equals(cname, category.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname);
    }
}
