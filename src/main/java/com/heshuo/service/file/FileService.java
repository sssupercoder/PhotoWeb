package com.heshuo.service.file;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author shoke
 * @Date 2021/12/14 15:33
 * @Version 1.0
 */
public interface FileService {
    void fileUpload(String imagePath, int id, String imageName);

    boolean fileSave(FileItem fileItem, int id) throws Exception;

    List getAllFile(int first, int length, HttpServletRequest request);

    List getUserFile(int id, int first, int length);

    int getAllFileCount();

    int getUserFileCount(int id);
}
