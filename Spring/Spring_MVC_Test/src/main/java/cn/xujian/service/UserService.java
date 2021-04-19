package cn.xujian.service;

import cn.xujian.domain.User;

import java.util.List;

public interface UserService {
    List<User> showUser();

    void save(User user, long[] roleIDs);

    void delUser(long userId);
}
