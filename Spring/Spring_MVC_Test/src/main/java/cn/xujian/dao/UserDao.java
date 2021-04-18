package cn.xujian.dao;

import cn.xujian.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUser();
}
