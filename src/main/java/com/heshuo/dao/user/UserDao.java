package com.heshuo.dao.user;

import com.heshuo.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author shoke
 * @Date 2021/12/10 12:51
 * @Version 1.0
 */
public interface UserDao {
    User getLoginUser(Connection connection, String username, String password) throws SQLException;
    boolean updateLastLoginTime(Connection connection, long lastLoginTime, int id) throws SQLException;
    boolean registerUser(Connection connection,String username,String password) throws SQLException;
}
