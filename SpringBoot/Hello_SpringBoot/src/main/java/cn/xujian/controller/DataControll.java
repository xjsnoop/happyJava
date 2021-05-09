package cn.xujian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

//1.1 java代码方式配置
//1.2 Spring Boot属性注入方式
@RestController
public class DataControll {

    @Autowired
    private DataSource dataSource;

    @GetMapping("data")
    public String showDataSource(){
        System.out.println(dataSource);
        return "dataSource ok!";
    }
}
