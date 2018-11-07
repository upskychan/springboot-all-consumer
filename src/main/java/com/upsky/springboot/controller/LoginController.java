package com.upsky.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 登录Controller。
 */

@Controller
public class LoginController {
    /**
     * 进入登录页面。
     * @param model 参数
     * @return 登录页面
     */
    @RequestMapping("/")
    public String loginPage(Model model){
        model.addAttribute("times",new Date());
        return "login";
    }

    /**
     * 登录验证，并进入index页面。
     * @param model 参数
     * @return index页面
     */
    @RequestMapping("/login")
    public String login(Model model){
        //TODO 验证用户登录
        return "index";
    }

    /**
     * 登出。
     * @param model 参数
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logOut(Model model){
        model.addAttribute("times",new Date());
        return "login";
    }

}
