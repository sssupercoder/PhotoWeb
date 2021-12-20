package com.heshuo.dao.file;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author shoke
 * @Date 2021/12/14 15:34
 * @Version 1.0
 */
public interface FileDao {
    //储存images表数据
    void setUploadFile(Connection connection, String imagePath, int id, String imageName) throws SQLException;
    //获取所有图片数
    int getFileCount(Connection connection) throws SQLException;
    //获取用户图片数
    int getFileCount(Connection connection,int id) throws SQLException;
    //获取广场图片
    List getFile(Connection connection, int first, int length, HttpServletRequest request) throws SQLException, UnknownHostException;
    //获取用户图片
    List getFile(Connection connection, int id, int first, int length) throws SQLException, UnknownHostException;

}
