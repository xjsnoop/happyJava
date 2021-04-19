package cn.xujian.tset;

import cn.xujian.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
//     查数据
    @Test
    public void test01() throws IOException {
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
//        打印数据
        System.out.println(userList);
//        释放资源
        sqlSession.close();
    }

//    增加数据
    @Test
    public void test02() throws IOException {
//        模拟user对象
        User user = new User();
        user.setUsername("tom");
        user.setPassword("hahaha");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.insert("userMapper.save",user);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }

    //    修改数据
    @Test
    public void test03() throws IOException {
//        模拟user对象
        User user = new User();
        user.setId(6);
        user.setUsername("tomDad");
        user.setPassword("123");
//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.update("userMapper.update",user);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }

    //    删除数据操作
    @Test
    public void test04() throws IOException {

//        获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        获取session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        获取session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        执行操作 参数：命名空间+id
        sqlSession.delete("userMapper.delete",6);
//        mybatis执行更新操作，必须提交事务
        sqlSession.commit();
//        释放资源
        sqlSession.close();
    }
}
