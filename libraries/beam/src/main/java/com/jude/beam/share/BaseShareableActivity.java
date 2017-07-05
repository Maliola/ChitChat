/*
 * Copyright (c) 2015. BiliBili Inc.
 */

package com.jude.beam.share;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.jude.beam.bijection.Presenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.share.helper.ShareHelper;
import com.lib.socialize.share.core.SocializeMedia;
import com.lib.socialize.share.core.shareparam.BaseShareParam;


/**
 * Share Helper Activity
 *
 *
 */
public abstract class BaseShareableActivity<T extends Presenter> extends BeamBaseActivity<T> implements ShareHelper.Callback {
    protected ShareHelper mShare;

    public void startShare(@Nullable View anchor) {
        startShare(anchor, false);
    }

    public void startShare(@Nullable View anchor, boolean isWindowFullScreen) {
        if (mShare == null) {
            mShare = ShareHelper.instance(this, this);
        }
        if (anchor == null) {
            mShare.showShareDialog();
        } else {
            if (isWindowFullScreen)
                mShare.showShareFullScreenWindow(anchor);
            else
                mShare.showShareWarpWindow(anchor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mShare != null) {
            mShare.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if (mShare != null) {
            mShare.onActivityDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onShareStart(ShareHelper helper) {

    }

    @Override
    public void onShareComplete(ShareHelper helper, int code) {

    }

    @Override
    public void onDismiss(ShareHelper helper) {

    }

    @Override
    public BaseShareParam getShareContent(ShareHelper helper, SocializeMedia target) {
        return null;
    }
}
