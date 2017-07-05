package com.example.ios007.chitchat.presenter.presenter;

import android.content.Intent;

import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.ui.activity.ChatActivity;
import com.example.ios007.chitchat.ui.fragment.ContactsFragment;
import com.example.ios007.chitchat.util.UtilData;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by ios007 on 2017/7/5.
 */

public class ContactsFragmentPresenter extends BeamListFragmentPresenter<ContactsFragment, Contact> {
    @Override
    protected void onCreateView(ContactsFragment view) {
        super.onCreateView(view);

        getAdapter().setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                UtilData.delContact((Contact) getAdapter().getAllData().get(position));
                onRefresh();
                return false;
            }
        });
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getView().getActivity(), ChatActivity.class);
                intent.putExtra("contact",(Contact)getAdapter().getAllData().get(position));
                getView().startActivity(intent);
                UtilData.addChatContact((Contact)getAdapter().getAllData().get(position));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAdapter().clear();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getAdapter().clear();
        getContact();
    }

    public void getContact(){
        getAdapter().addAll(UtilData.getContact());
    }
}
