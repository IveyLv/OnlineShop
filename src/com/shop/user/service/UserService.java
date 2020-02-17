package com.shop.user.service;

import com.shop.user.dao.UserDao;
import com.shop.user.vo.User;
import com.shop.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户模块业务层代码
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 按用户名查询用户
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 保存用户（用户注册）
     */
    public void save(User user) {
        user.setState(0);
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setCode(code);
        userDao.save(user);
        // 发送激活邮件
    }

    /**
     * 根据激活码查询用户
     */
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    /**
     * 修改用户状态
     */
    public void update(User existUser) {
        userDao.update(existUser);
    }

    /**
     * 用户登录的方法
     */
    public User login(String username, String password) {
        return userDao.login(username, password);
    }
}
