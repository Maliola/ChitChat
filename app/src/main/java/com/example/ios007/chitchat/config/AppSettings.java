package com.example.ios007.chitchat.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ios007.chitchat.model.User;

/**
 * Created by ios007 on 2017/7/5.
 */

public class AppSettings {
    public static final String SHARED_PREFERENCES_NAME = "com.example.ios007.chitchat.config.settings";
    private SharedPreferences mGlobalPreferences;

    public AppSettings(Context context) {
        mGlobalPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public final StringPreference COOKIE_INFO_PASSWORD = new StringPreference(ParamsUtils.COOKIE_INFO_PASSWORD, "");
    public final StringPreference COOKIE_INFO_ROLENAME = new StringPreference(ParamsUtils.COOKIE_INFO_ROLENAME, "");
    public final StringPreference COOKIE_INFO_PORTRAIT = new StringPreference(ParamsUtils.COOKIE_INFO_PORTRAIT, "");
    public final StringPreference COOKIE_INFO_USERDESC = new StringPreference(ParamsUtils.COOKIE_INFO_USERDESC, "");

    /**
     * 将用户信息持久化
     *
     * @param user
     */
    public void saveUser(User user) {
        COOKIE_INFO_ROLENAME.setValue(user.getNickName());
        COOKIE_INFO_PORTRAIT.setValue(user.getAvatar());
        COOKIE_INFO_PASSWORD.setValue(user.getPassword());
        COOKIE_INFO_USERDESC.setValue(user.getDesc());
    }



    /**
     * 获取当前用户信息
     *
     * @return
     */
    public User getUser() {
        User user = new User();
        user.setNickName(COOKIE_INFO_ROLENAME.getValue());
        user.setAvatar(COOKIE_INFO_PORTRAIT.getValue());
        user.setDesc(COOKIE_INFO_USERDESC.getValue());
        return user;
    }

    /**
     * 清空用户信息
     */
    public void clearUserInfo() {
        COOKIE_INFO_PASSWORD.resetToDefault();
        COOKIE_INFO_ROLENAME.resetToDefault();
        COOKIE_INFO_PORTRAIT.resetToDefault();
        COOKIE_INFO_USERDESC.resetToDefault();
    }

    // ///////////////////////////////////////

    public abstract class CommonPreference<T> {
        private final String id;
        private T defaultValue;

        public CommonPreference(String id, T defaultValue) {
            this.id = id;
            this.defaultValue = defaultValue;
        }

        protected T getDefaultValue() {
            return defaultValue;
        }

        public abstract T getValue();

        public abstract boolean setValue(T val);

        public String getId() {
            return id;
        }

        /**
         * 重置为初始值
         */
        public void resetToDefault() {
            setValue(getDefaultValue());
        }
    }

    /**
     * 整型数值参数保存
     */
    public class IntPreference extends CommonPreference<Integer> {
        private IntPreference(String id, int defaultValue) {
            super(id, defaultValue);
        }

        @Override
        public Integer getValue() {
            return mGlobalPreferences.getInt(getId(), getDefaultValue());
        }

        @Override
        public boolean setValue(Integer val) {
            return mGlobalPreferences.edit().putInt(getId(), val).commit();
        }
    }

    /**
     * 长整型数值参数保存
     */
    public class LongPreference extends CommonPreference<Long> {
        private LongPreference(String id, long defaultValue) {
            super(id, defaultValue);
        }

        @Override
        public Long getValue() {
            return mGlobalPreferences.getLong(getId(), getDefaultValue());
        }

        @Override
        public boolean setValue(Long val) {
            return mGlobalPreferences.edit().putLong(getId(), val).commit();
        }
    }

    /**
     * 布尔型参数保存
     */
    public class BooleanPreference extends CommonPreference<Boolean> {
        private BooleanPreference(String id, boolean defaultValue) {
            super(id, defaultValue);
        }

        @Override
        public Boolean getValue() {
            return mGlobalPreferences.getBoolean(getId(), getDefaultValue());
        }

        @Override
        public boolean setValue(Boolean val) {
            return mGlobalPreferences.edit().putBoolean(getId(), val).commit();
        }
    }

    /**
     * String参数保存
     */
    public class StringPreference extends CommonPreference<String> {

        public StringPreference(String id, String defaultValue) {
            super(id, defaultValue);
        }

        @Override
        public String getValue() {
            return mGlobalPreferences.getString(getId(), getDefaultValue());
        }

        @Override
        public boolean setValue(String val) {
            return mGlobalPreferences.edit().putString(getId(), val).commit();
        }
    }

    /**
     * 枚举型设置参数保存，适用于多选一。
     */
    public class EnumIntPreference<E extends Enum<E>> extends CommonPreference<E> {
        private final E[] values;

        private EnumIntPreference(String id, E defaultValue, E[] values) {
            super(id, defaultValue);
            this.values = values;
        }

        @Override
        public E getValue() {
            try {
                int i = mGlobalPreferences.getInt(getId(), -1);
                if (i >= 0 && i < values.length) {
                    return values[i];
                }
            } catch (ClassCastException ex) {
                setValue(getDefaultValue());
            }
            return getDefaultValue();
        }

        @Override
        public boolean setValue(E val) {
            return mGlobalPreferences.edit().putInt(getId(), val.ordinal()).commit();
        }
    }
    public String arr2String(String[] str){
        if(null!=str){

            int n=str.length;
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<n;i++){
                sb.append(str[i]+",");
            }
            return sb.toString();
        }else{
            return null;
        }
    }
}
