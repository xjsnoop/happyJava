package cn.xujian;

import cn.xujian.domain.User;
import cn.xujian.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {
//    <if>测试用例
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//模拟用户
        User condition = new User();
        condition.setId(5);
        condition.setUsername("宇琴");
        condition.setPassword("123456");

        List<User> userList = mapper.findByCondition(condition);
        System.out.println(userList);
    }
//    <foreach>测试用例
    @Test
    public void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//       模拟测试数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(3);
        ids.add(5);
        ids.add(6);

        List<User> userList = mapper.findByIds(ids);
        System.out.println(userList);

    }
}
