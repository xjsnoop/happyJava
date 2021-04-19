package cn.xujian.controller;

import cn.xujian.domain.Role;
import cn.xujian.domain.User;
import cn.xujian.service.RoleService;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
//查询所有user数据并展示
    @RequestMapping("/list")
    public ModelAndView showUser(){
       List<User> userList = userService.showUser();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("userList",userList);
       modelAndView.setViewName("user-list");
        return modelAndView;
    }
//    查询User有的角色并返回显示
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.showList();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return  modelAndView;
    }
//    新增user数据
    @RequestMapping("/save")
    public String save(User user,long[] roleIDs){
        userService.save(user,roleIDs);
        return "redirect:/user/list";
    }

//    删除单条user数据
    @RequestMapping("/del/{userId}")
    public String delUser(@PathVariable("userId") long userId){
        userService.delUser(userId);
        return "redirect:/user/list";
    }
}
