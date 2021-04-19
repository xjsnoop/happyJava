package cn.xujian.service.impl;

import cn.xujian.dao.RoleDao;
import cn.xujian.dao.UserDao;
import cn.xujian.domain.Role;
import cn.xujian.domain.User;
import cn.xujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void save(User user, long[] roleIDs) {
//        向sys_user表中存储数据
        long userId = userDao.save(user);
//        向sys_user_role关系表中存储多条数据
        userDao.saveUserRoleRel(userId,roleIDs);
    }

    @Override
    public void delUser(long userId) {
//        删除关系表相关数据
        userDao.delUserRoleRel(userId);
//        删除user表相关数据
        userDao.delUserById(userId);
    }
}
