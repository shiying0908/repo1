package com.it666.dao;

import com.it666.domain.User;

public interface UserDao {
    public User getUser(String username, String password);

}
