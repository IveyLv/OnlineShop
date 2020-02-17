package com.shop.adminuser.dao;

import com.shop.adminuser.vo.AdminUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 后台用户持久层对象
 */
@Repository
public class AdminUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 后台用户登录的方法
     */
    public AdminUser login(AdminUser adminUser) {
        String hql = "from AdminUser where username=? and password=?";
        Query query = getSession().createQuery(hql);

        query.setString(0, adminUser.getUsername())
                .setString(1, adminUser.getPassword());
        List<AdminUser> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
