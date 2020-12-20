package cn.xujian.test;

import cn.xujian.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void test1(){
        //读取spring的配置文件创建容器
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获得对象
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        UserDao userDao2 = (UserDao) app.getBean("userDao");
        //打印对象的地址
        System.out.println(userDao1);
        System.out.println(userDao2);
        //关闭容器
        ((ClassPathXmlApplicationContext)app).close();

    }
}
