package cn.xujian.service;

import cn.xujian.domain.Account;

import java.util.List;

public interface AccountService {
//    保存账户数据
    public void save(Account account);
//    查询账户数据
    public List<Account> findAll();
}
