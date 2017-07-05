package com.example.ios007.chitchat.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ios007.chitchat.R;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ios007 on 2017/7/5.
 */

public class SplashActivity extends BeamBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_loginindex);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.bt_login)
    public void login(){
        Intent intent=new Intent(SplashActivity.this,LoginAcitivity.class);
        startActivity(intent);
    }
}
