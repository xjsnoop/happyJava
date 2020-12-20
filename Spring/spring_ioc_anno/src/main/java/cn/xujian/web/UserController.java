package cn.xujian.web;

import cn.xujian.service.UserService;
import cn.xujian.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserServiceImpl)app.getBean("userService");
        userService.save();

    }
}
