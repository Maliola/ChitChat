package com.example.ios007.chitchat.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.model.Contact;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by ios007 on 2017/7/5.
 */

public class AddFriendViewHolder extends BaseViewHolder<Contact> {
    SimpleDraweeView contact_portrait;
    TextView contact_name,add_friend;
    public AddFriendViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_addfriend);
        contact_portrait=$(R.id.contact_portrait);
        contact_name=$(R.id.contact_name);
        add_friend=$(R.id.add_friend);
    }

    @Override
    public void setData(Contact data) {
        super.setData(data);
        contact_portrait.setImageURI(data.getContact_portrait());
        contact_name.setText(data.getContact_name());
        if(data.isfriend()){
            add_friend.setText("已添加");
        }else{
            add_friend.setText("添加");
        }
    }
}
