package com.upsky.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class CommonController {
    @RequestMapping("/404")
    public String errorPage(Model model){
        model.addAttribute("times",new Date());
        return "404";
    }

    @RequestMapping("/admin-user.html")
    public String userPage(){
        return "admin-user";
    }

    @RequestMapping("/admin-help.html")
    public String helpPage(){
        return "admin-help";
    }

    @RequestMapping("/admin-gallery.html")
    public String galleryPage(){
        return "admin-gallery";
    }

    @RequestMapping("/admin-log.html")
    public String logPage(){
        return "admin-log";
    }

    @RequestMapping("/admin-table.html")
    public String tablePage(){
        return "admin-table";
    }

    @RequestMapping("/admin-form.html")
    public String formPage(){
        return "admin-form";
    }
}
