package cn.xujian.factory;

import cn.xujian.dao.UserDao;
import cn.xujian.dao.impl.UserDaoImpl;

public class DynamicFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
