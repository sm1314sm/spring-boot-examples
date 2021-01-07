package com.neo.dao;

import com.neo.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo, Integer> {
    /**
     * 通过username查找用户信息
     */
    UserInfo findByUsername(String username);
}