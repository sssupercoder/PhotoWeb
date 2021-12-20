package com.heshuo.pojo;

import java.util.Date;

/**
 * @Author shoke
 * @Date 2021/12/10 10:06
 * @Version 1.0
 */
public class User {
    private int id;
    private String username;
    private String password;
    private long lastLoginTime;

    public User() {
    }

    public User(int id, String username, String password, long lastLoginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
