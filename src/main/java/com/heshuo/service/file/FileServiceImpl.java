package com.heshuo.service.file;
import com.heshuo.dao.BaseDao;
import com.heshuo.dao.file.FileDao;
import com.heshuo.dao.file.FileDaoImpl;
import com.heshuo.util.Constants;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @Author shoke
 * @Date 2021/12/14 15:55
 * @Version 1.0
 */
public class FileServiceImpl implements FileService {
    private FileDao fileDao;

    public FileServiceImpl() {
        fileDao = new FileDaoImpl();
    }

    @Override
    public void fileUpload(String imagePath, int id, String imageName) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        try {
            fileDao.setUploadFile(connection, imagePath, id, imageName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
    }

    @Override
    public boolean fileSave(FileItem fileItem, int id) throws Exception {
        //用于判断是否保存成功
        boolean flag = false;
        try {
            File uploadDir = new File(Constants.UPLOAD_DIRECTORY);
            // 如果目录不存在则创建
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //文件名
            String imageName = new File(fileItem.getName()).getName();
            //文件后缀
            String suffix = imageName.substring(imageName.lastIndexOf(".") + 1);
            //UUID做文件路径
            String imagePath = UUID.randomUUID().toString() + '.' + suffix;
            String filePath = Constants.UPLOAD_DIRECTORY + File.separator + imagePath;
            File storeFile = new File(filePath);
            // 在控制台输出文件的上传路径
            System.out.println(filePath);
            // 保存文件到硬盘
            fileItem.write(storeFile);
            // 保存信息到数据库
            this.fileUpload(imagePath, id, imageName);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List getAllFile(int first, int length,HttpServletRequest request) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        List allFile = null;
        try {
            allFile = fileDao.getFile(connection, first, length,request);
        } catch (SQLException | UnknownHostException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return allFile;
    }

    @Override
    public List getUserFile(int id, int first, int length) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        List allFile = null;
        try {
            allFile = fileDao.getFile(connection, id, first, length);
        } catch (SQLException | UnknownHostException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return allFile;
    }

    @Override
    public int getAllFileCount() {
        Connection connection = null;
        connection = BaseDao.getConnection();
        int fileCount = -1;
        try {
            fileCount = fileDao.getFileCount(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileCount;
    }

    @Override
    public int getUserFileCount(int id) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        int fileCount = -1;
        try {
            fileCount = fileDao.getFileCount(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileCount;
    }

    @Test
    public void test() {

        System.out.println(this.getUserFile(1, 0, 10));
    }
}
