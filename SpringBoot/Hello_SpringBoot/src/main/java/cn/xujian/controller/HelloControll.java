package cn.xujian.controller;

import cn.xujian.pojo.User;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControll {
    @Autowired
    private UserService userService;
    /**
     * @param: id
     * @return: user
     * @auther: xj
     * @date: 2021-05-09 22:14
     * @description:根据用户id查询并返回用户
    */
    @GetMapping("user/{id}")
    public User queryById(@PathVariable Long id){
        return userService.query(id);
    }

    /**
     * @param: user
     * @return:
     * @auther: xj
     * @date: 2021-05-09 22:23
     * @description:插入数据
    */
    @GetMapping("user/add")
    public void addUser(){
        //    模拟一个user对象
        User user = new User("徐健","333");
        userService.saveUser(user);
    }
}
