package com.heshuo.util;

import java.io.File;

/**
 * @Author shoke
 * @Date 2021/12/8 20:00
 * @Version 1.0
 */
public class Constants {
    //session
    public final static String USER_SESSION = "userSession";
    //登录成功后的重定向页面
    public final static String TRUE_LOGIN_REDIRECT = "upload.html";
    //图片相关
    public static final long serialVersionUID = 1L;
    // 上传文件存储目录
    public static final String UPLOAD_DIRECTORY = "E:" + File.separator + "images";
    //上传文件映射目录
    public static final String IMAGE_PATH = "/images/";
    //映射端口
    public static final int PORT=8080;
    // 上传配置
    // 设置内存临界值
    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    // 最大文件上传值
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    // 设置最大请求值 (包含文件和表单数据)
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
}
