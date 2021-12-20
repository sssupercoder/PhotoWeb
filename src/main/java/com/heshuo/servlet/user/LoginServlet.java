package com.heshuo.servlet.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heshuo.pojo.User;
import com.heshuo.service.user.UserService;
import com.heshuo.service.user.UserServiceImpl;
import com.heshuo.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author shoke
 * @Date 2021/12/10 12:45
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);

        if (user != null) {
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            userService.setLastLoginTime(System.currentTimeMillis(), user.getId());
            resp.sendRedirect(Constants.TRUE_LOGIN_REDIRECT);

        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "ERROR");
            jsonObject.put("message", "用户名或密码错误");
            ServletOutputStream out = resp.getOutputStream();
            out.write(JSON.toJSONBytes(jsonObject));
            out.flush();
            out.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
