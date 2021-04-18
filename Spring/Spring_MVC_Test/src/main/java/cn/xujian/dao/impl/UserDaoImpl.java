package cn.xujian.dao.impl;

import cn.xujian.dao.UserDao;
import cn.xujian.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUser() {
        List<User> userList = jdbcTemplate.query("select * from sys_user",new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
}
