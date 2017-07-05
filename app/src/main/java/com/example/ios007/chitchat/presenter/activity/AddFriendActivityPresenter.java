package com.example.ios007.chitchat.presenter.activity;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.model.Contact;
import com.example.ios007.chitchat.ui.activity.AddFriendAcitivity;
import com.example.ios007.chitchat.util.UtilData;
import com.example.ios007.chitchat.widge.DividerGridItemDecoration;
import com.jude.beam.expansion.list.NavigationListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by ios007 on 2017/7/5.
 */

public class AddFriendActivityPresenter extends NavigationListActivityPresenter<AddFriendAcitivity,Contact> implements RecyclerArrayAdapter.OnItemClickListener{

    @Override
    protected void onCreateView(AddFriendAcitivity view) {
        super.onCreateView(view);
        getView().getListView().addItemDecoration(new DividerGridItemDecoration(getView().getResources(),
                R.color.white, R.dimen.nav_divider_height, R.dimen.nav_divider_height, DividerGridItemDecoration.GRID));
        getView().getListView().setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        getAdapter().setOnItemClickListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getRecommendContact();
    }

    public void getRecommendContact(){
        getAdapter().addAll(UtilData.getRecommend());
    }

    public void searchFriend(){
        Toast.makeText(getView(),"找不到",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getView(),"添加成功"+getAdapter().getAllData().get(position).getContact_name(),Toast.LENGTH_SHORT).show();
        UtilData.addContact(getAdapter().getAllData().get(position));
        getRecommendContact();
    }
}
