package com.heshuo.dao.love;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author shoke
 * @Date 2021/12/20 16:31
 * @Version 1.0
 */
public interface LoveDao {
    boolean isLove(Connection connection,int id,String imagePath) throws SQLException;
    boolean addLove(Connection connection,int id,String imagePath);
}
