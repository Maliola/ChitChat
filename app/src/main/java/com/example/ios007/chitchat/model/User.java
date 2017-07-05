package com.example.ios007.chitchat.model;

/**
 * Created by ios007 on 2017/7/5.
 */

public class User {
    private int id;
    private String avatar;
    private String nickName;
    private String desc;
    private String password;

    public User() {
    }

    public User(int id, String avatar, String nickName, String desc, String password) {
        this.id = id;
        this.avatar = avatar;
        this.nickName = nickName;
        this.desc = desc;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
