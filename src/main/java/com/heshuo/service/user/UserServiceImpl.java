package com.heshuo.service.user;

import com.heshuo.dao.BaseDao;
import com.heshuo.dao.user.UserDao;
import com.heshuo.dao.user.UserDaoImpl;
import com.heshuo.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @Author shoke
 * @Date 2021/12/10 13:08
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    //业务层都会调用dao层,所以我们要引入dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String username, String password) {
        Connection connection = null;
        User user = null;
        connection = BaseDao.getConnection();
        try {
            user = userDao.getLoginUser(connection, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    public boolean setLastLoginTime(long lastLoginTime, int id){
        Connection connection = null;
        connection = BaseDao.getConnection();
        boolean flag=false;
        try {
            flag= userDao.updateLastLoginTime(connection,lastLoginTime,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public boolean userRegister(String username, String password) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        try {
            return userDao.registerUser(connection,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return false;
    }

    @Test
    public void test(){
        UserService userService = new UserServiceImpl();
        boolean flag=userService.userRegister("aaa","ccc");
        if (!flag){
            System.out.println("已存在该用户");
        }else {
            System.out.println("注册成功!");
        }
    }
}
