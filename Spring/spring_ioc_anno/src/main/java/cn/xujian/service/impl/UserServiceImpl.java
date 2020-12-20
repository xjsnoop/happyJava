package cn.xujian.service.impl;

import cn.xujian.dao.UserDao;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//    <bean id="userService" class="cn.xujian.service.impl.UserServiceImpl"></bean>
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {
    @Value("徐健")
    private String name;
//    <property name="userDao" ref="userDao"></property>
//    向userService注入userDao类
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.save();
        System.out.println(name);
    }
}
