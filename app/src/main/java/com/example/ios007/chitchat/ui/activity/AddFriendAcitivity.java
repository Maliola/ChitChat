package com.example.ios007.chitchat.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.presenter.activity.AddFriendActivityPresenter;
import com.example.ios007.chitchat.ui.viewholder.AddFriendViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beam.expansion.list.NavigationListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by ios007 on 2017/7/5.
 */
@RequiresPresenter(AddFriendActivityPresenter.class)
public class AddFriendAcitivity extends NavigationListActivity<AddFriendActivityPresenter,Contact> implements TextView.OnEditorActionListener{
    TextView search;
    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(true).setContainerLayoutRes(R.layout.activity_addfriend);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search= (TextView) findViewById(R.id.search);
        search.setOnEditorActionListener(this);
    }
    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AddFriendViewHolder(parent);
    }
    @Override
    public void initData() {
        super.initData();
        setLeftDrawable(R.drawable.ic_back_selector);
        setTitle("加好友");
    }
    @Override
    protected void onLeftCLick() {
        super.onLeftCLick();
        finish();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (null == event) {
            return false;
        } else if (event.getAction() == KeyEvent.ACTION_DOWN && (actionId == EditorInfo.IME_ACTION_SEND || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            if (search.getText().toString().trim().length() == 0) {
                Toast.makeText(AddFriendAcitivity.this,"输入不能为空",Toast.LENGTH_SHORT);
                return false;
            }
            getPresenter().searchFriend();
        }
        return false;
    }
}
