package com.heshuo.service.user;

import com.heshuo.pojo.User;

import java.sql.Date;

/**
 * @Author shoke
 * @Date 2021/12/10 13:07
 * @Version 1.0
 */
public interface UserService {
    public User login(String username,String password);
    public boolean setLastLoginTime(long lastLoginTime, int id);
    public boolean userRegister(String username,String password);
}
