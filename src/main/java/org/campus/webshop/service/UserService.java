package org.campus.webshop.service;

import org.campus.webshop.dao.UserDao;
import org.campus.webshop.entity.User;

public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return user;
    }

    public User add(User user){
        User resultUser = userDao.add(user);
        return resultUser;
    }
}
