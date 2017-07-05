package com.example.ios007.chitchat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.config.ChitChatApllication;
import com.example.ios007.chitchat.model.User;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.BeamFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by stone on 2017/6/15.
 */

public class MineFragment extends BeamFragment {
    @Bind(R.id.mine_potrait)
    SimpleDraweeView mine_potrait;
    @Bind(R.id.mine_username)
    TextView mine_username;
    @Bind(R.id.user_desc)
    TextView user_desc;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = ChitChatApllication.getInstance().getUser();
        mine_potrait.setImageURI(user.getAvatar());
        mine_username.setText(user.getNickName());
        user_desc.setText(user.getDesc());
    }
    @OnClick(R.id.mine_setting)
    public void jumpSetting(View view){
        /*Intent intent=new Intent(getActivity(),SettingActivity.class);
        startActivity(intent);*/
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
