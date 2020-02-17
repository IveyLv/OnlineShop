package com.shop.user.controller;

import com.shop.utils.IdentCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 生成验证码类
 */
@Controller
public class CheckImgController {

    @RequestMapping("checkImg")
    public String checkImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IdentCodeUtils.getIdentCode(request, response);
        return null;
    }
}
