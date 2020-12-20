package cn.xujian.test;

import cn.xujan.domain.Acount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate(){
        jdbcTemplate.update("update account set balance=? where name = ?",100,"超人");
    }
    @Test
    public void testDelete(){
        jdbcTemplate.update("delete from account where name = ?","tom");
    }
    //查询所有对象
    @Test
    public void testQueryAll(){
        List<Acount> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Acount>(Acount.class));
        System.out.println(accountList.get(1));
    }
    //查询一个对象
    @Test
    public void testQuertOne(){
        Acount acountOne = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<Acount>(Acount.class), "超人");
        System.out.println(acountOne);
    }
    //聚合查询
    @Test
    public void testQueryCount(){
        Long count = jdbcTemplate.queryForObject("select count(*) from account", long.class);
        System.out.println(count);
    }

}
