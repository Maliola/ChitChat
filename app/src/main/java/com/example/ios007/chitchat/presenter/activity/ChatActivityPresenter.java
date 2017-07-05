package com.example.ios007.chitchat.presenter.activity;

import com.example.ios007.chitchat.ui.activity.ChatActivity;
import com.example.ios007.chitchat.util.UtilData;
import com.jude.beam.expansion.list.NavigationListActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ios007 on 2017/7/5.
 */

public class ChatActivityPresenter extends NavigationListActivityPresenter<ChatActivity,String> {
    List<String> chats=new ArrayList<String>();
    @Override
    protected void onCreateView(ChatActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getAdapter().clear();
        getRecord();
    }

    public void getRecord(){
        getAdapter().addAll(UtilData.getChats());
    }
    public void setChat(String neitong){
        chats.add(neitong);
        getAdapter().addAll(chats);
        UtilData.addChat(neitong);
        //onRefresh();
        scollToBottom();
    }
    private void scollToBottom() {
        getView().getListView().scrollToPosition(chats.size()+4);
    }
}
