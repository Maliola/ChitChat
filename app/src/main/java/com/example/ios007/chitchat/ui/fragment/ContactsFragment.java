package com.example.ios007.chitchat.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.presenter.presenter.ContactsFragmentPresenter;
import com.example.ios007.chitchat.ui.activity.AddFriendAcitivity;
import com.example.ios007.chitchat.ui.viewholder.ContactViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by ios007 on 2017/7/5.
 */
@RequiresPresenter(ContactsFragmentPresenter.class)
public class ContactsFragment extends BeamListFragment<ContactsFragmentPresenter,Contact>{
    TextView search_jump;

    @Override
    public BaseViewHolder<Contact> getViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(parent);
    }

    @Override
    public ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true).setContainerLayoutRes(R.layout.fragment_contactlist);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        search_jump= (TextView) getView().findViewById(R.id.search_jump);
        search_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddFriendAcitivity.class);
                startActivity(intent);
            }
        });
    }
}
