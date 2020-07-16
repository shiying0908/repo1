package com.it666.service.impl;

import com.it666.dao.UserDao;
import com.it666.domain.User;
import com.it666.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

@Transactional//加入事务
public class LoginServiceImpl implements LoginService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public User login(User user) {
        System.out.println("service用户名是="+user.getUsername());
        User resUser = userDao.getUser(user.getUsername(), user.getPassword());
        return resUser;
    }
}
