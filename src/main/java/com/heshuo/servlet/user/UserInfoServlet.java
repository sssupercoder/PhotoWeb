package com.heshuo.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.heshuo.pojo.User;
import com.heshuo.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author shoke
 * @Date 2021/12/21 14:27
 * @Version 1.0
 */
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        JSONObject object = new JSONObject();
        if (user != null) {
            object.put("msg", "OK");
            object.put("username", user.getUsername());
            object.put("lastLoginTime", user.getLastLoginTime());
        } else {
            object.put("msg", "ERROR");
        }
        OutputStream out = resp.getOutputStream();
        out.write(object.toString().getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

}
