package com.upsky.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.upsky.springboot.model.User;
import com.upsky.springboot.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    private int pageSize = 5;//每页展示10条记录

    @Reference //调用远程服务
    private IUserService userService;

    /**
     * 分页展示用户列表。
     * @param model 返回值
     * @return 用户列表
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "curPage",required = false) Integer curPage , Model model){
        int totalRows = userService.getTotalUser();//总记录数

        if (null == curPage || curPage < 1) {//默认显示第一页内容
            curPage = 1;
        }

        //计算分页
        int totalPages = totalRows / pageSize;
        int left = totalRows % pageSize;
        if(left > 0) {//有余数
            totalPages = totalPages + 1;
        }

        if (curPage > totalPages) {
            curPage = totalPages;
        }

        //计算查询的起始位置
        int startRow = (curPage - 1) * pageSize;
        Map<String,Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("startRow",startRow);
        paramMap.put("pageSize",pageSize);
        List<User> userList = userService.listUsersByPage(paramMap);

        model.addAttribute("userList",userList);
        model.addAttribute("totalRows",totalRows);
        model.addAttribute("curPage",curPage);
        model.addAttribute("totalPages",totalPages);

        return "user/list";
    }

    /**
     * 添加用户。
     * @return
     */
    @RequestMapping("/add")
    public String addUserPage(){
        return "user/add";
    }

    /**
     * 保存用户信息。
     * @param user
     * @return
     */
    @RequestMapping("/save")
    public String saveUser(User user, @RequestParam(value = "curPage",required = false) Integer curPage, RedirectAttributes redirectAttributes){
        Integer id = user.getId();
        if(null == id) {
            userService.addUser(user);
        }else {
            userService.updateUser(user);
        }
        if(null == curPage) {
            //curPage = 1;
        }else {
            redirectAttributes.addAttribute("curPage",curPage);
        }
//        return "redirect:/user/list?curPage="+curPage;
        return "redirect:/user/list";
    }

    /**
     * 更新用户信息。
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdateUser(@RequestParam(value = "id") Integer userId, @RequestParam(value = "curPage") Integer curPage, Model model){
        User user = userService.getUserById(userId);
        model.addAttribute("user",user);
        model.addAttribute("curPage",curPage);
        return "user/add";
    }

    /**
     * 删除用户。
     * @param userId
     * @return
     */
    @RequestMapping("/delete")
    public String delUser(@RequestParam(value = "id") Integer userId){
        userService.deleteUser(userId);
        return "redirect:/user/list";
    }

}
