package org.campus.webshop.dao;

import org.campus.webshop.entity.User;

public interface UserDao {
    User findByEmail(String email);

    User add(User user);
}
