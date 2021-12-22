package com.heshuo.service.love;

import com.heshuo.dao.BaseDao;
import com.heshuo.dao.love.LoveDao;
import com.heshuo.dao.love.LoveDaoImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author shoke
 * @Date 2021/12/21 13:30
 * @Version 1.0
 */
public class LoveServiceImpl implements LoveService {
    private LoveDao loveDao;

    public LoveServiceImpl() {
        loveDao = new LoveDaoImpl();
    }

    @Override
    public boolean addLove(String imagePath, int id) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        try {
            loveDao.addLove(connection, id, imagePath);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return true;
    }
    @Test
    public void test(){
        boolean b = this.addLove("d5bb5886-0a88-41cb-aade-1b2d252c7892.jpg", 7);
        System.out.println(b);
    }
}
