package com.heshuo.servlet.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heshuo.service.file.FileService;
import com.heshuo.service.file.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author shoke
 * @Date 2021/12/19 15:43
 * @Version 1.0
 */
public class PhotoGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //显示页
        int ps;
        //显示数
        int pn;
        try {
            ps = Integer.parseInt(req.getParameter("ps"));
            pn = Integer.parseInt(req.getParameter("pn"));
        } catch (NumberFormatException nfe) {
            req.getRequestDispatcher("/api/allphoto?ps=1&pn=20").forward(req, resp);
            return;
        }
        FileService fileService = new FileServiceImpl();
        //所有的相片数量
        int allFileCount = fileService.getAllFileCount();
        //防止错误值
        if (ps < 1) ps = 1;
        if (pn < 1) pn = 20;
        int beginRow = (ps - 1) * pn;
        List allFile = fileService.getAllFile(beginRow, pn, req);
        JSONObject jsonObject = new JSONObject();
        boolean isLast = ps * pn >= allFileCount;
        jsonObject.put("page", ps);
        jsonObject.put("count", pn);
        jsonObject.put("list", allFile);
        jsonObject.put("isLast", isLast);
        ServletOutputStream out = resp.getOutputStream();
        out.write(JSON.toJSONBytes(jsonObject));
        out.flush();
        out.close();
    }
}
