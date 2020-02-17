package com.shop.user.dao;

import com.shop.user.vo.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户模块持久层代码
 */
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    // 不推荐使用 HibernateTemplate 和 HibernateDaoSupport
    // 因为这样会导致 Dao 和 Spring 的 API 进行耦合, 可以移植性变差
    //private HibernateTemplate hibernateTemplate

    // 获取和当前线程绑定的 Session
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public User findByUsername(String username) {
        // 1. 创建 Query对象
        // 基于位置的参数。
        String hql = "from User where username=?";
        Query query = getSession().createQuery(hql);

        // 2. 绑定参数
        // Query对象调用setXxx方法支持方法链的编程风格。
        query.setString(0, username);

        // 3. 执行查询
        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    // 注册用户存入数据库
    public void save(User user) {
        getSession().save(user);
    }

    // 根据激活码查询用户
    public User findByCode(String code) {
        String hql = "from User where code=?";
        Query query = getSession().createQuery(hql);

        query.setString(0, code);
        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    // 修改用户状态
    public void update(User existUser) {
        getSession().update(existUser);
    }

    /**
     * 用户登录
     */
    public User login(String username, String password) {
        String hql = "from User where username=? and password=? and state=1";
        Query query = getSession().createQuery(hql);

        query.setString(0, username)
             .setString(1, password);
        List<User> users = query.list();

        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
