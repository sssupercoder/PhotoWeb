package com.heshuo.pojo;

/**
 * @Author shoke
 * @Date 2021/12/10 10:08
 * @Version 1.0
 */
public class Love {
    private String imagePath;
    private int id;

    public Love() {
    }

    public Love(String imagePath, int id) {
        this.imagePath = imagePath;
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
