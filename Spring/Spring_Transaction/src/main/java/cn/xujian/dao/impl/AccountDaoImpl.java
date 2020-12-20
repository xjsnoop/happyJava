package cn.xujian.dao.impl;

import cn.xujian.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void in(String inMan,double money) {
       jdbcTemplate.update("update account set balance= balance+? where name = ?",money,inMan);
    }

    @Override
    public void out(String outMan,double money) {
        jdbcTemplate.update("update account set balance= balance-? where name = ?",money,outMan);
    }
}
