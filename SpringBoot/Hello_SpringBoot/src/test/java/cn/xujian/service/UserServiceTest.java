package cn.xujian.service;

import cn.xujian.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired UserService userService;
    @Test
    void query() {
        User user = userService.query(8L);
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUser_name("阿涂2");
        user.setAge(25);
        user.setPassword("0000");
        user.setCreated(new Date());
        userService.saveUser(user);
    }
}