package com.example.ios007.chitchat.model;

import java.io.Serializable;

/**
 * Created by ios007 on 2017/7/5.
 */

public class Contact implements Serializable{
    private int id;
    private String contact_portrait;
    private String contact_name;
    private boolean isfriend;
    public Contact() {
    }

    public Contact(int id, String contact_portrait, String contact_name, boolean isfriend) {
        this.id = id;
        this.contact_portrait = contact_portrait;
        this.contact_name = contact_name;
        this.isfriend = isfriend;
    }

    public boolean isfriend() {
        return isfriend;
    }

    public void setIsfriend(boolean isfriend) {
        this.isfriend = isfriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact_portrait() {
        return contact_portrait;
    }

    public void setContact_portrait(String contact_portrait) {
        this.contact_portrait = contact_portrait;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
