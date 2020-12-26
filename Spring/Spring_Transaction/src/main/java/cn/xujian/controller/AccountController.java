package cn.xujian.controller;

import cn.xujian.service.AccountService;
import cn.xujian.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext_transaction.xml");
        AccountService as = app.getBean(AccountService.class);
        as.transfer("张三","李四",100);
    }
}
