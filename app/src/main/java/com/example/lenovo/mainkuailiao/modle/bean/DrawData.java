package com.example.lenovo.mainkuailiao.modle.bean;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/5 13:45
 */

public class DrawData {
    private int image;
    private String draname;

    public DrawData(int image, String draname) {
        this.image = image;
        this.draname = draname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDraname() {
        return draname;
    }

    public void setDraname(String draname) {
        this.draname = draname;
    }

}
