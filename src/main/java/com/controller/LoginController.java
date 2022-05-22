package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //登录
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String uname, @RequestParam("password") String pword, Model model, HttpSession session){
        if (!StringUtils.isEmpty(uname) && "123456".equals(pword)){
            session.setAttribute("loginUser",uname);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
        }
        return "index";
    }

    //注销
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }

}
