package cn.xujian.service.impl;

import cn.xujian.dao.AccountDao;
import cn.xujian.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String inMan, String outMan, double money) {
        accountDao.in(inMan,money);

        accountDao.out(outMan,money);
        int i = 1/0;

    }
}
