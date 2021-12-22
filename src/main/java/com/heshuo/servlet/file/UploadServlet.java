package com.heshuo.servlet.file;

import com.alibaba.fastjson.JSONObject;
import com.heshuo.pojo.User;
import com.heshuo.service.file.FileService;
import com.heshuo.service.file.FileServiceImpl;
import com.heshuo.util.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author shoke
 * @Date 2021/12/14 16:47
 * @Version 1.0
 */
//@CrossOrigin
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(Constants.MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(Constants.MAX_FILE_SIZE);
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(Constants.MAX_REQUEST_SIZE);
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        //返回json给前端
        JSONObject object = new JSONObject();
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            FileService fileService = new FileServiceImpl();
            User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    fileService.fileSave(item, user.getId());
                }
            }
            object.put("msg", "ok");
            object.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("msg", "error");
            object.put("code", 0);
        } finally {
            OutputStream out = resp.getOutputStream();
            out.write(object.toString().getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
