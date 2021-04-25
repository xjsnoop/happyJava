package cn.xujian;

import cn.xujian.domain.Orders;
import cn.xujian.domain.User;
import cn.xujian.domain.User1;
import cn.xujian.mapper.OrderMapper;
import cn.xujian.mapper.User1Mapper;
import cn.xujian.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

//    测试一对一查询
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Orders> ordersList = mapper.findAll();
        for (Orders orders :ordersList){
            System.out.println(orders);
        }
        sqlSession.close();
    }

//    测试一对多查询
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.findAll();
        System.out.println(userList);
        sqlSession.close();
    }

    //    测试多对多查询
    @Test
    public void test03() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User1Mapper mapper = sqlSession.getMapper(User1Mapper.class);

        List<User1> userAndRole = mapper.findUserAndRole();
        System.out.println(userAndRole);
        sqlSession.close();
    }

}
