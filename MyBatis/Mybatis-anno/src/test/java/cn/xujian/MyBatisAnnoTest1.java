package cn.xujian;

import cn.xujian.domain.Orders;
import cn.xujian.domain.User;
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

public class MyBatisAnnoTest1 {
    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }
    //    使用注解的一对多查询
    @Test
    public void testFindUserAndOrder(){
        List<User> userAndOrder = mapper.findUserAndOrder();
        System.out.println(userAndOrder);
    }
//    使用注解的多对多查询
    @Test
    public void testFindUserAndRole(){
        List<User> userAndRole = mapper.findUserAndRole();
        System.out.println(userAndRole);
    }
}
