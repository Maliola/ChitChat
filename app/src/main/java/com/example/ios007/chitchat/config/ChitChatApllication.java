package com.example.ios007.chitchat.config;

import android.app.Application;

import com.example.ios007.chitchat.model.User;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ios007 on 2017/7/5.
 */

public class ChitChatApllication extends Application{
    public static ChitChatApllication instance;
    private AppSettings settings;
    public static ChitChatApllication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Fresco.initialize(this);
    }
    public AppSettings getAppSettings() {
        if (settings == null) {
            settings = new AppSettings(instance);
        }
        return settings;
    }

    public User getUser() {
        return getAppSettings().getUser();
    }
}
