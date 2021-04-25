package cn.xujian.mapper;

import cn.xujian.domain.User;

import java.util.List;

public interface UserMapper {
    public void save (User user);
    public List<User> findAll();
}
