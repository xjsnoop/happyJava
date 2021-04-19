package cn.xujian.dao;

import cn.xujian.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUser();

    long save(User user);

    void saveUserRoleRel(Long userId, long[] roleIDs);

    void delUserRoleRel(long userId);

    void delUserById(long userId);
}
