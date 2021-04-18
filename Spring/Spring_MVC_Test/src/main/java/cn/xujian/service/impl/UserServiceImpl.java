package cn.xujian.service.impl;

import cn.xujian.dao.RoleDao;
import cn.xujian.dao.UserDao;
import cn.xujian.domain.Role;
import cn.xujian.domain.User;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> showUser() {
        List<User> userList = userDao.findAllUser();
        for (User user : userList){
//            获取user的id
            Long id = user.getId();
            List<Role> roles = roleDao.findRoleById(id);
            user.setRoles(roles);
        }
        return userList;
    }
}
