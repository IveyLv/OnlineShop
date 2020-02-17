package com.shop.adminuser.service;

import com.shop.adminuser.dao.AdminUserDao;
import com.shop.adminuser.vo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台用户业务层对象
 */
@Service
@Transactional
public class AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    /**
     * 业务层后台用户登录
     */
    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
