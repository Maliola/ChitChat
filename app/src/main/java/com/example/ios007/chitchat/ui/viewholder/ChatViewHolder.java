package com.example.ios007.chitchat.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ios007.chitchat.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by ios007 on 2017/7/5.
 */

public class ChatViewHolder extends BaseViewHolder<String> {
    TextView chat_neirong;
    public ChatViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_chat);
        chat_neirong=$(R.id.chat_neirong);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
        chat_neirong.setText(data);
    }
}