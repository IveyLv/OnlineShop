package com.shop.adminuser.controller;

import com.shop.adminuser.service.AdminUserService;
import com.shop.adminuser.vo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/**
 * 后台用户表现层对象
 */
@Controller
@SessionAttributes(value = {"existAdminUser"})
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 用户登录的方法
     */
    @RequestMapping("adminUser_login")
    public String login(AdminUser adminUser, Map<String, Object> map) {
        // 调用 Service 完成登录
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if (existAdminUser == null) {
            // 登录失败
            map.put("loginMsg", "用户名或密码错误！");
            return "admin/index";
        } else {
            // 登录成功
            map.put("existAdminUser", existAdminUser);
            return "admin/home";
        }
    }

    @RequestMapping("admin_top")
    public String top() {
        return "admin/top";
    }

    @RequestMapping("admin_left")
    public String left() {
        return "admin/left";
    }

    @RequestMapping("admin_welcome")
    public String welcome() {
        return "admin/welcome";
    }

    @RequestMapping("admin_bottom")
    public String bottom() {
        return "admin/bottom";
    }
}
