package cn.xujian.service.impl;

import cn.xujian.dao.UserDao;
import cn.xujian.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserServiceImpl() {
    }

    //    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
    @Override
    public void save() {
        userDao.save();
    }
}
