package cn.xujian.test;

import cn.xujian.config.SpringConfiguration;
import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSourceTest {
    @Test
    //测试手动创建C3P0数据源
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置驱动
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        //设置连接url
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/db1");
        //设置用户名
        dataSource.setUser("root");
        //设置密码
        dataSource.setPassword("xj");
        //获取资源连接
        Connection connection = dataSource.getConnection();
        //打印地址
        System.out.println(connection);
        //归还资源
        connection.close();
    }
    @Test
    //测试手动创建Druid数据源
    public void test2() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        //设置驱动
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接url
        dataSource.setUrl("jdbc:mysql://localhost:3306/db1");
        //设置用户名
        dataSource.setUsername("root");
        //设置密码
        dataSource.setPassword("xj");
        //获取资源连接
        Connection connection = dataSource.getConnection();
        //打印地址
        System.out.println(connection);
        //归还资源
        connection.close();
    }
    @Test
    //测试手动创建C3P0数据源,(加载配置文件的方式)
    public void test3() throws Exception {
    //读取配置文件
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("jdbc.driver");
        String url = resourceBundle.getString("jdbc.url");
        String username = resourceBundle.getString("jdbc.username");
        String password = resourceBundle.getString("jdbc.password");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置驱动
        dataSource.setDriverClass(driver);
        //设置连接url
        dataSource.setJdbcUrl(url);
        //设置用户名
        dataSource.setUser(username);
        //设置密码
        dataSource.setPassword(password);
        //获取资源连接
        Connection connection = dataSource.getConnection();
        //打印地址
        System.out.println(connection);
        //归还资源
        connection.close();
    }
    @Test
    //测试Spring容器产生数据源对象   C3P0连接池
    public void test4() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource-C3P0");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    //测试Spring容器产生数据源对象  Druid连接池
    public void test5() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource-Druid");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    //测试通过注解配置的类，Spring容器产生数据源对象  Druid连接池
    public void test6() throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource-Druid-an");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
