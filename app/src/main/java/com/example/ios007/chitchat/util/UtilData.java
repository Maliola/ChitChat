package com.example.ios007.chitchat.util;

import com.example.ios007.chitchat.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ios007 on 2017/7/5.
 */

public class UtilData {
    static List<Contact> recommend=new ArrayList<Contact>();
    static List<Contact> contacts=new ArrayList<Contact>();
    static List<Contact> chatcontacts=new ArrayList<Contact>();
    static List<String> chats=new ArrayList<String>();
    static{
        contacts.add(new Contact(0,"","网二",true));
        contacts.add(new Contact(1,"","李四",true));
        contacts.add(new Contact(2,"","赵飞燕",true));
        contacts.add(new Contact(3,"","娜扎",true));
        contacts.add(new Contact(4,"","哪吒",true));
        recommend.add(new Contact(0,"","网二",true));
        recommend.add(new Contact(5,"","热巴",false));
        recommend.add(new Contact(6,"","败笔",false));
        recommend.add(new Contact(7,"","赵薇",false));
        recommend.add(new Contact(8,"","宁静",false));
        chatcontacts.add(new Contact(0,"","网二",true));
        chatcontacts.add(new Contact(1,"","李四",true));
        chats.add("你好啊");
        chats.add("你好啊");
        chats.add("你好啊");
        chats.add("你好啊");
        chats.add("你好啊");
    }
    public static List<Contact> getContact(){
        return contacts;
    }
    public static List<Contact> getRecommend(){
        return recommend;
    }
    public static List<Contact> getChatcontacts(){
        return chatcontacts;
    }
    public static void addChatContact(Contact contact){
        chatcontacts.add(contact);
    }
    public static void delChatContact(Contact contact){
        chatcontacts.remove(contact);
    }
    public static void addContact(Contact contact){
        contacts.add(contact);
    }
    public static void delContact(Contact contact){
        contacts.remove(contact);
    }
    public static List<String> getChats(){
        return chats;
    }
    public static void addChat(String neirong){
        chats.add(neirong);
    }
}
