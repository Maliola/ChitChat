package com.example.ios007.chitchat.presenter.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.ui.activity.ChatActivity;
import com.example.ios007.chitchat.ui.fragment.ChatRecordFragment;
import com.example.ios007.chitchat.util.UtilData;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by ios007 on 2017/7/5.
 */

public class ChatRecordFragmentPresenter extends BeamListFragmentPresenter<ChatRecordFragment, Contact> {
    @Override
    protected void onCreateView(@NonNull ChatRecordFragment view) {
        super.onCreateView(view);
        getAdapter().setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                UtilData.delChatContact((Contact) getAdapter().getAllData().get(position));
                onRefresh();
                return false;
            }
        });
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getView().getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getAdapter().clear();
        getChatRecord();
    }
    public void getChatRecord(){
        getAdapter().addAll(UtilData.getChatcontacts());
    }
}
