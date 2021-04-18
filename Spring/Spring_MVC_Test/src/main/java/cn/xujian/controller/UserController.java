package cn.xujian.controller;

import cn.xujian.domain.User;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView showUser(){
       List<User> userList = userService.showUser();
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("userList",userList);
       modelAndView.setViewName("user-list");
        return modelAndView;
    }
}
