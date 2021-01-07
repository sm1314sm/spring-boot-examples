package com.neo.repository;

import com.neo.domain.User;

public interface UserRepository {
    void saveUser(User user);

    User findUserByUserName(String userName);

    long updateUser(User user);

    void deleteUserById(Long id);
}
