package com.shop.user.controller;

import com.shop.user.service.UserService;
import com.shop.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用户模块控制层代码
 */
@Controller
@SessionAttributes(value = {"activeMsg", "activeCode", "existUser"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 跳转到注册页面的方法
     */
    @RequestMapping("user_register_page")
    public String userRegisterPage() {
        return "register";
    }

    /**
     * AJAX 进行异步校验用户名的方法
     */
    @RequestMapping(value = "user_findByUsername", method = RequestMethod.GET)
    public String findByUsername(@RequestParam(value = "username") String username,
                               HttpServletResponse response) throws IOException {
        System.out.println("username" + username);
        // 调用 Service 进行查询
        User existUser = userService.findByUsername(username);
        response.setContentType("text/html;charset=UTF-8");
        // 判断
        if (existUser != null) {
            // 查询到该用户：用户名已经存在
            response.getWriter().write("<font color='red'>用户名已经存在</font>");
        } else {
            // 没查询到该用户：用户名可以使用
            response.getWriter().write("<font color='green'>用户名可以使用</font>");
        }
        return null;
    }

    /**
     * 用户注册的方法
     */
    @RequestMapping(value = "user_register", method = RequestMethod.POST)
    public String userRegister(User user, @RequestParam("checkCode") String checkCode, Map<String, String> map) {
        // 判断验证码
        String identCode = (String) request.getSession().getAttribute("identCode");
        if (!checkCode.equalsIgnoreCase(identCode)) {
            map.put("registerMsg", "验证码错误");
            return "register";
        }

        //System.out.println("User: " + user);
        userService.save(user);
        map.put("activeMsg", "注册成功！请去邮箱激活！");
        map.put("activeCode", user.getCode());
        return "redirect:toMsg";
    }

    @RequestMapping("toMsg")
    public String toMsg() {
        return "msg";
    }

    /**
     * 用户激活的方法
     */
    @RequestMapping("user_active")
    public String active(@RequestParam("code") String code, Map<String, String> map) {
        // 根据激活码查询用户
        User existUser = userService.findByCode(code);
        // 判断
        if (existUser == null) {
            // 激活码错误
            map.put("activeMsg", "激活失败：激活码错误。");
        } else {
            // 激活成功
            existUser.setState(1);
            existUser.setCode(null);
            userService.update(existUser);
            map.put("activeMsg", "激活成功：请登录！");
        }
        return "msg";
    }

    /**
     * 跳转到登录界面
     */
    @RequestMapping("user_login_Page")
    public String userLoginPage() {
        return "login";
    }

    /**
     * 用户登录的方法
     */
    @RequestMapping("user_login")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("checkCode") String checkCode, Map<String, Object> map) {
        // 判断验证码
        String identCode = (String) request.getSession().getAttribute("identCode");
        if (!checkCode.equalsIgnoreCase(identCode)) {
            map.put("loginMsg", "验证码错误");
            return "login";
        }

        User existUser = userService.login(username, password);
        // 判断
        if (existUser == null) {
            // 登录失败
            map.put("loginMsg", "用户名或密码不正确, 或账号未激活");
            return "login";
        } else {
            // 登录成功
            // 将用户信息存储到 Session 中
            map.put("existUser", existUser);
            return "index";
        }
    }

    /**
     * 用户退出的方法
     */
    @RequestMapping("user_quit")
    public String quit(SessionStatus status) {
        // 销毁 Session 对象
        //System.out.println("session1:" + request.getSession().getAttribute("existUser"));
        //request.getSession().invalidate();
        // sessionStatus中的setComplete方法可以将session中的内容全部清空
        status.setComplete();
        //System.out.println("session2:" + request.getSession().getAttribute("existUser"));
        return "redirect:/index.jsp";
    }
}
