package cn.xujian.service.impl;

import cn.xujian.dao.RoleDao;
import cn.xujian.domain.Role;
import cn.xujian.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> showList() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }
}
