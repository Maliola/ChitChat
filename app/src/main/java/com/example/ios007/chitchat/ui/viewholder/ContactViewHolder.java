package com.example.ios007.chitchat.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.model.Contact;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by stone on 2017/6/15.
 */

public class ContactViewHolder extends BaseViewHolder<Contact> {
    private SimpleDraweeView contact_portrait;
    private TextView contact_name;

    public ContactViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_contact);
        contact_portrait=$(R.id.contact_portrait);
        contact_name=$(R.id.contact_name);
    }

    @Override
    public void setData(Contact data) {
        super.setData(data);
        contact_portrait.setImageURI(data.getContact_portrait());
        contact_name.setText(data.getContact_name());
    }
}
