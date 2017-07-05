package com.example.ios007.chitchat.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.presenter.activity.ChatActivityPresenter;
import com.example.ios007.chitchat.ui.viewholder.ChatViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beam.expansion.list.NavigationListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by ios007 on 2017/7/5.
 */
@RequiresPresenter(ChatActivityPresenter.class)
public class ChatActivity extends NavigationListActivity<ChatActivityPresenter,String> {
    EditText input;
    Button send;
    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true).setContainerLayoutRes(R.layout.activity_chatrecord);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        input= (EditText) findViewById(R.id.input);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString().trim().length()>0){
                    getPresenter().setChat(input.getText().toString().trim());
                    input.setText("");
                }else{
                    Toast.makeText(ChatActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new ChatViewHolder(parent);
    }
    @Override
    public void initData() {
        super.initData();
        setLeftDrawable(R.drawable.ic_back_selector);
        setTitle("聊天记录");
    }
    @Override
    protected void onLeftCLick() {
        super.onLeftCLick();
        finish();
    }

}
