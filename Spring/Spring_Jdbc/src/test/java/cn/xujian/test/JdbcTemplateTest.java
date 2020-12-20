package cn.xujian.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class JdbcTemplateTest {
    //测试JdbcTemplate开发步骤
    @Test
    public void test1(){
        DruidDataSource dataSource = new DruidDataSource();
        //设置驱动
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接url
        dataSource.setUrl("jdbc:mysql://localhost:3306/db1");
        //设置用户名
        dataSource.setUsername("root");
        //设置密码
        dataSource.setPassword("xj");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //设置数据源对象，知道数据在哪里
        jdbcTemplate.setDataSource(dataSource);
        //执行操作
        jdbcTemplate.update("insert into account (name,balance) value (?,?)","tom",4900);
    }
    //测试Spring产生jdbc模板对象
    @Test
    public void test2(){
        ApplicationContext app =new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate)app.getBean("jdbcTemplate");
        jdbcTemplate.update("insert into account (name,balance) value (?,?)","钢铁侠",100001);
    }
}
