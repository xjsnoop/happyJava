package cn.xujian.dao;

import cn.xujian.domain.Manager;

public interface ManagerDao {
    public Manager findByUsernameAndPassword(String username, String password);
}
