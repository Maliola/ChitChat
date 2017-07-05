package com.example.ios007.chitchat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.config.AppSettings;
import com.example.ios007.chitchat.config.ChitChatApllication;
import com.example.ios007.chitchat.model.User;
import com.example.ios007.chitchat.widge.ClearEditText;
import com.jude.beam.expansion.NavigationBarActivity;

/**
 * Created by ios007 on 2017/7/5.
 */

public class LoginAcitivity extends NavigationBarActivity{
    ClearEditText regist_text_mobile,regist_text_pwd;
    Button regitry_button_regist;
    CheckBox login_show_pwd_bt;
    @Override
    protected View onCreateView(Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.activity_login, null);
        regist_text_mobile= (ClearEditText) view.findViewById(R.id.regist_text_mobile);
        regist_text_pwd= (ClearEditText) view.findViewById(R.id.regist_text_pwd);
        regitry_button_regist= (Button) view.findViewById(R.id.regitry_button_regist);
        login_show_pwd_bt= (CheckBox) view.findViewById(R.id.login_show_pwd_bt);
        login_show_pwd_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                if (isChecked) {
                    regist_text_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    regist_text_pwd.setSelection(regist_text_pwd.getText().length());
                } else {
                    regist_text_pwd.setInputType(type);
                    regist_text_pwd.setSelection(regist_text_pwd.getText().length());
                }
            }
        });
        regitry_button_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regist_text_mobile.getText().toString().trim().length()>0&&regist_text_pwd.getText().toString().trim().length()>0){
                    User user=new User(0,"",regist_text_mobile.getText().toString().trim().toString(),"好人",regist_text_pwd.getText().toString().trim().toString());
                    ChitChatApllication application=ChitChatApllication.getInstance();
                    if(null!=application){
                        AppSettings appSettings= application.getAppSettings();
                        if(null!=appSettings){
                            appSettings.saveUser(user);
                        }
                    }

                    Intent intent=new Intent(LoginAcitivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        setLeftDrawable(R.drawable.ic_back_selector);
        setTitle("登录");
    }

    @Override
    protected void onLeftCLick() {
        super.onLeftCLick();
        finish();
    }
}
