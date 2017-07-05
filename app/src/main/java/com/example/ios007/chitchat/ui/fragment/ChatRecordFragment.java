package com.example.ios007.chitchat.ui.fragment;

import android.view.ViewGroup;

import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.presenter.presenter.ChatRecordFragmentPresenter;
import com.example.ios007.chitchat.ui.viewholder.ContactViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by ios007 on 2017/7/5.
 */
@RequiresPresenter(ChatRecordFragmentPresenter.class)
public class ChatRecordFragment extends BeamListFragment<ChatRecordFragmentPresenter,Contact>{
    @Override
    public ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true);
    }

    @Override
    public BaseViewHolder<Contact> getViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(parent);
    }
}
