package cn.xujian;

import cn.xujian.domain.Orders;
import cn.xujian.mapper.OrderMapper;
import cn.xujian.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisAnnoTest {
    private OrderMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(OrderMapper.class);
    }

//    一对一查询注解封装测试
    @Test
    public void tset1(){
        List<Orders> ordersList = mapper.findAll();
        System.out.println(ordersList);
    }
    @Test
    public void tset2(){
        List<Orders> ordersList = mapper.findAll02();
        System.out.println(ordersList);
    }
}
