package com.example.lenovo.mainkuailiao.modle.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/6 9:46
 */
@Entity
public class MassagedbData {
    @Id(autoincrement = true)
    private Long id;
    private String imageurl;
    private String username;
    @Generated(hash = 25099620)
    public MassagedbData(Long id, String imageurl, String username) {
        this.id = id;
        this.imageurl = imageurl;
        this.username = username;
    }
    @Generated(hash = 1017711505)
    public MassagedbData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageurl() {
        return this.imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
