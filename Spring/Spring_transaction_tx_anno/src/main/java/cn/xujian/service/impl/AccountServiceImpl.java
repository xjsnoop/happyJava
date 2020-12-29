package cn.xujian.service.impl;

import cn.xujian.dao.AccountDao;
import cn.xujian.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
//    给方法加事务
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void transfer(String inMan, String outMan, double money) {
        accountDao.in(inMan,money);
//        int i = 1/0;
        accountDao.out(outMan,money);


    }
}
