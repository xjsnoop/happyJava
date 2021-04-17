package cn.xujian.service.impl;

import cn.xujian.dao.ManagerDao;
import cn.xujian.domain.Manager;
import cn.xujian.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public Manager login(String username, String password) {
        Manager manager = managerDao.findByUsernameAndPassword(username, password);
        return manager;
    }
}
