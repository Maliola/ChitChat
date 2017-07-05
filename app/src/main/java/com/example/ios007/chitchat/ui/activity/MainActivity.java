package com.example.ios007.chitchat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.ios007.chitchat.R;
import com.example.ios007.chitchat.ui.fragment.ChatRecordFragment;
import com.example.ios007.chitchat.ui.fragment.ContactsFragment;
import com.example.ios007.chitchat.ui.fragment.MineFragment;
import com.jude.beam.expansion.BeamBaseActivity;

import java.util.HashMap;

import static android.R.id.tabhost;

public class MainActivity extends BeamBaseActivity implements View.OnClickListener {

    private int DEF_TAB_COUNT = 3;
    // 底部Tab栏
    private TabHost mTabHost;
    private TabManager mTabManager;
    private LinearLayout mBottomTabs;
    private Button[] mTabButtons;
    public int mCurrentIdx = 0;
    private int tab = 2;
    private ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView() {
        icon = (ImageView) findViewById(R.id.icon);
        mTabHost = (TabHost) findViewById(tabhost);
        mTabHost.setup();
        initContent();
        initTabButtons();
        mTabButtons[0].setSelected(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                for (int i = 0; i < mTabButtons.length; i++) {
                    mTabButtons[i].setSelected(false);
                    if (v == mTabButtons[i]) {
                        v.setSelected(true);
                        mCurrentIdx = i;
                        mTabHost.setCurrentTab(mCurrentIdx);
                    }
                }
                break;
        }
    }



    protected void initTabButtons() {
        mBottomTabs = (LinearLayout) findViewById(R.id.main_radio);
        if (null == mBottomTabs) {
            throw new IllegalArgumentException("Your TabHost must have a RadioGroup whose id attribute is 'main_radio'");
        }

        mTabButtons = new Button[DEF_TAB_COUNT];
        for (int j = 0; j < DEF_TAB_COUNT; j++) {
            String str = "radio_button_" + j;
            mTabButtons[j] = (Button) mBottomTabs.findViewWithTag(str);
            mTabButtons[j].setOnClickListener(this);
        }
        mTabButtons[0].performClick();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }



    protected void initContent() {
        mTabManager = new TabManager(this, mTabHost, R.id.real_tab_content);
        mTabManager.addTab(mTabHost.newTabSpec("1").setIndicator("One"), ChatRecordFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("2").setIndicator("Two"), ContactsFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec("3").setIndicator("Three"), MineFragment.class, null);
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }


    public long exitTime = 0;

    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * This is a helper class that implements a generic mechanism for
     * associating fragments with the tabs in a tab host. It relies on a trick.
     * Normally a tab host has a simple API for supplying a View or Intent that
     * each tab will show. This is not sufficient for switching between
     * fragments. So instead we make the content part of the tab host 0dp high
     * (it is not shown) and the TabManager supplies its own dummy view to show
     * as the tab content. It listens to changes in tabs, and takes care of
     * switch to the correct fragment shown in a separate content area whenever
     * the selected tab changes.
     */
    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentActivity mActivity;
        private final TabHost mTabHost;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerId = containerId;
            mTabHost.setOnTabChangedListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state. If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                ft.detach(info.fragment);
                ft.commitAllowingStateLoss();
            }

            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        public Fragment getLastTabFragment() {
            return mLastTab.fragment;
        }

        @Override
        public void onTabChanged(String tabId) {

            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
                        ft.hide(mLastTab.fragment);
                        // ft.detach(mLastTab.fragment);
                    }
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity, newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
                        if (newTab.fragment.isHidden())
                            ft.show(newTab.fragment);
                        else
                            ft.attach(newTab.fragment);
                    }
                }
                mLastTab = newTab;
                ft.commitAllowingStateLoss();
                mActivity.getSupportFragmentManager().executePendingTransactions();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //SocializeUtils.onActivityResult(MainActivity.this, requestCode, resultCode, data);
    }
}

