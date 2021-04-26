package cn.xujian;

import cn.xujian.domain.User;
import cn.xujian.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }
//    增
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("小贱贱");
        user.setPassword("abc");
        mapper.save(user);
    }
//    改
    @Test
    public void tsetUpdate(){
        User user = new User();
        user.setId(11);
        user.setUsername("小贱");
        user.setPassword("abc");
        mapper.update(user);
    }
//    删
    @Test
    public void tsetDelete(){
        mapper.delete(11);
    }
//    查
    @Test
    public void tsetFindById(){
        User user = mapper.findById(8);
        System.out.println(user);
    }

    @Test
    public void tsetFindAll(){
        List<User> userList = mapper.findAll();
        System.out.println(userList);
    }


}
