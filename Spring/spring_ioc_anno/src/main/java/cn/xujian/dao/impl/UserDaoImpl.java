package cn.xujian.dao.impl;

import cn.xujian.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//<bean id="userDao" class="cn.xujian.dao.impl.UserDaoImpl"></bean>
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("save running....");
    }
}
