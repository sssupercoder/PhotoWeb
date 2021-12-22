package com.heshuo.dao.file;

import com.heshuo.dao.BaseDao;
import com.heshuo.dao.love.LoveDao;
import com.heshuo.dao.love.LoveDaoImpl;
import com.heshuo.pojo.User;
import com.heshuo.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.heshuo.util.Constants.*;

/**
 * @Author shoke
 * @Date 2021/12/14 15:34
 * @Version 1.0
 */
public class FileDaoImpl implements FileDao {

    @Override
    public void setUploadFile(Connection connection, String imagePath, int id, String imageName) throws SQLException {
        PreparedStatement preparedStatement = null;
        Object[] params = {imagePath, id, imageName};
        String sql = "insert into images(imagePath, id,imageName) values (?,?,?)";
        BaseDao.execute(connection, sql, params, preparedStatement);
    }

    @Override
    public int getFileCount(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select count(imagePath) from images";
        Object[] params = {};
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        int row = -1;
        if (resultSet.next()) {
            row = resultSet.getInt("count(imagePath)");
        }
        return row;
    }

    @Override
    public int getFileCount(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object[] params = {id};
        String sql = "select count(imagePath) from images where id=?";
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        int row = -1;
        if (resultSet.next()) {
            row = resultSet.getInt("count(imagePath)");
        }
        return row;
    }

    @Override
    public List getFile(Connection connection, int first, int length, HttpServletRequest request) throws SQLException, UnknownHostException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select i.imagePath,(select count(imagePath) from love where i.imagePath=love.imagePath) as a from images i limit ?,?";
        Object[] params = {first, length};
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        List<List> image_ALL = new ArrayList<>();
        LoveDao love = new LoveDaoImpl();
        while (resultSet.next()) {
            List<String> image = new ArrayList<>();
            User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
            if (user != null) {
                boolean isLove = love.isLove(connection, user.getId(), resultSet.getString("imagePath"));
                image.add(REAL_IP + ":" + PORT + IMAGE_PATH + resultSet.getString("imagePath"));
                image.add(String.valueOf(resultSet.getInt("a")));
                image.add(String.valueOf(isLove));
            } else {
                image.add(REAL_IP + ":" + PORT + IMAGE_PATH + resultSet.getString("imagePath"));
                image.add(String.valueOf(resultSet.getInt("a")));
                image.add("false");
            }
            image_ALL.add(image);
        }
        return image_ALL;
    }

    @Override
    public List getFile(Connection connection, int id, int first, int length) throws SQLException, UnknownHostException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select i.imagePath,(select count(imagePath) from love where i.imagePath=love.imagePath) as a from images i where id=? limit ?,?";
        Object[] params = {id, first, length};
        resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
        List<List> image_ALL = new ArrayList<>();
        while (resultSet.next()) {
            List<String> image = new ArrayList<>();
            image.add(REAL_IP + ":" + PORT + IMAGE_PATH + resultSet.getString("imagePath"));
            image.add(String.valueOf(resultSet.getInt("a")));
            image_ALL.add(image);
        }
        return image_ALL;
    }
}
