package cn.xujian.dao.impl;

import cn.xujian.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void in(String inMan,double money) {
       jdbcTemplate.update("update account set balance= balance+? where name = ?",money,inMan);
    }

    @Override
    public void out(String outMan,double money) {
        jdbcTemplate.update("update account set balance= balance-? where name = ?",money,outMan);
    }
}
