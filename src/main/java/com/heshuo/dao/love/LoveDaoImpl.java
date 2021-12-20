package com.heshuo.dao.love;

import com.heshuo.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author shoke
 * @Date 2021/12/20 16:31
 * @Version 1.0
 */
public class LoveDaoImpl implements LoveDao {
    @Override
    public boolean isLove(Connection connection, int id, String imagePath) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select id from love where imagePath=? and id=?";
        Object[] params = {imagePath, id};
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addLove(Connection connection, int id, String imagePath) {
        PreparedStatement preparedStatement = null;
        return false;
    }
}
