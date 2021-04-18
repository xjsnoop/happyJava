package cn.xujian.dao;

import cn.xujian.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();

    void addRole(Role role);

    List<Role> findRoleById(Long id);
}
