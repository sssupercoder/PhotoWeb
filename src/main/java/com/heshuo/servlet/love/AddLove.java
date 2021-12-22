package com.heshuo.servlet.love;

import com.alibaba.fastjson.JSONObject;
import com.heshuo.pojo.User;
import com.heshuo.service.love.LoveService;
import com.heshuo.service.love.LoveServiceImpl;
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
 * @Date 2021/12/22 14:58
 * @Version 1.0
 */
public class AddLove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getParameter("img");
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        LoveService loveService = new LoveServiceImpl();
        JSONObject object = new JSONObject();
        if (user == null) {
            object.put("code", "0");
        } else {
            object.put("code", "200");
            boolean flag = loveService.addLove(imagePath, user.getId());
            if (flag) object.put("msg", "点赞成功");
            else object.put("msg", "点赞失败");
        }
        OutputStream out = resp.getOutputStream();
        out.write(object.toString().getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
