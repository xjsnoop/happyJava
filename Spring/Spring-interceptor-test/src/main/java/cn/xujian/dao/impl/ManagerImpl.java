package cn.xujian.dao.impl;

import cn.xujian.dao.ManagerDao;
import cn.xujian.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("managerDao")
public class ManagerImpl implements ManagerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Manager findByUsernameAndPassword(String username, String password) {
        Manager manager;

        try {
            manager = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
        } catch (DataAccessException e) {
            manager=null;
        }
        return manager;
    }
}
