package cn.xujian.controller;

import cn.xujian.domain.Manager;
import cn.xujian.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @RequestMapping("/login")
    public String login (String username, String password, HttpSession session){
        Manager manager = managerService.login(username, password);
//        密码和账户对应
        if (manager!=null){
            session.setAttribute("manager",manager);

            return "redirect:/index01.jsp";
        }
        return "/login.jsp";
    }
    @RequestMapping("/quick1")
    public String quick1(){
        return "/quick1.jsp";
    }
}
