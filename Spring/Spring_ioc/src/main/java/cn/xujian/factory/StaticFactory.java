package cn.xujian.factory;

import cn.xujian.dao.UserDao;
import cn.xujian.dao.impl.UserDaoImpl;
//静态工厂方法
public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
