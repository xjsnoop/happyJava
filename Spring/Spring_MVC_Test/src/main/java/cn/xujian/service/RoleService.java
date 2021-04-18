package cn.xujian.service;

import cn.xujian.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> showList();

    void addRole(Role role);
}
