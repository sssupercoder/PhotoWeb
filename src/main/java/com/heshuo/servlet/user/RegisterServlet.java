package com.heshuo.servlet.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heshuo.service.user.UserService;
import com.heshuo.service.user.UserServiceImpl;
import com.heshuo.util.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author shoke
 * @Date 2021/12/13 12:45
 * @Version 1.0
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        boolean flag = userService.userRegister(username, password);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put("code", "200");
            jsonObject.put("msg", "注册成功");
            resp.sendRedirect("login.html");
        } else {
            jsonObject.put("code", "0");
            jsonObject.put("msg", "注册失败");
        }
        ServletOutputStream out = resp.getOutputStream();
        out.write(JSON.toJSONBytes(jsonObject));
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
