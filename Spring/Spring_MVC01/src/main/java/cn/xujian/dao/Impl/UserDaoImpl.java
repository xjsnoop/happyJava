package cn.xujian.dao.Impl;

import cn.xujian.dao.UserDao;
import org.springframework.stereotype.Repository;


public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save running");
    }
}
