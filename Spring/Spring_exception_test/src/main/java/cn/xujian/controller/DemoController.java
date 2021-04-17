package cn.xujian.controller;

import cn.xujian.exception.MyException;
import cn.xujian.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@Controller
public class DemoController {
    @Autowired
    private DemoService demoService;
    @RequestMapping("/show")
    public String show() throws FileNotFoundException, MyException {
        System.out.println("show Running.....");
//        demoService.show1();
//        demoService.show2();
//        demoService.show3();
        demoService.show4();
//        demoService.show5();
        return "/index.jsp";
    }
}
