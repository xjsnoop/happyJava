package org.example;

import cn.xujian.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
//    查询
    @Test
    public void  findAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql语句
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印结果
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }

    //    插入数据
    @Test
    public void  add() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //模拟数据
        User user = new User();
        user.setUsername("徐健");
        user.setPassword("12345");
        //执行sql语句
        int insert = sqlSession.insert("userMapper.add", user);
        System.out.println(insert);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //    修改数据
    @Test
    public void  update() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //模拟数据
        User user = new User();
        user.setId(5);
        user.setUsername("宇琴");
        user.setPassword("123456");
        //执行sql语句
        int update = sqlSession.update("userMapper.update", user);
        System.out.println(update);
        sqlSession.commit();
        sqlSession.close();
    }

    //    删除数据
    @Test
    public void  delete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int delete = sqlSession.delete("userMapper.delete",10);
        System.out.println(delete);
        sqlSession.commit();
        sqlSession.close();
    }
}