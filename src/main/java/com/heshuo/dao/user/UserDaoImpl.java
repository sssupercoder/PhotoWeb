package com.heshuo.dao.user;

import com.heshuo.dao.BaseDao;
import com.heshuo.pojo.User;

import java.sql.*;

/**
 * @Author shoke
 * @Date 2021/12/10 12:53
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(Connection connection, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        String sql = "select * from user where username=? and password=?";
        Object[] params = {username, password};

        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setLastLoginTime(resultSet.getLong("lastLoginTime"));
        }
        BaseDao.closeResource(null, preparedStatement, resultSet);
        return user;
    }

    @Override
    public boolean updateLastLoginTime(Connection connection, long lastLoginTime, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "update user set lastLoginTime=? where id=?";
        Object[] params = {lastLoginTime, id};
        BaseDao.execute(connection, sql, params, preparedStatement);
        return true;
    }

    @Override
    public boolean registerUser(Connection connection, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from user where username=?";
        Object[] params = {username};
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        //判断是否存在注册的用户
        if (!resultSet.next()) {
            sql = "insert into user (username, password) values (?,?)";
            params = new Object[]{username, password};
            BaseDao.execute(connection, sql, params, preparedStatement);
            return true;
        } else {
            return false;
        }
    }
}
