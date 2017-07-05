package com.lib.socialize.pay.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lib.socialize.pay.PayManager;
import com.lib.socialize.share.R;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public abstract class WXPayCallbackActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("WXPayCallbackActivity", "onCreate");
        setContentView(R.layout.activity_wxpay_call_back);
        api = WXAPIFactory.createWXAPI(this, getAppId());
        api.handleIntent(getIntent(), this);
    }

    public abstract String getAppId();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("WXPayCallbackActivity", "onNewIntent");
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.e("WXPayCallbackActivity", "onReq");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("WXPayCallbackActivity", "onResp");
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            PayManager.getInstance().onResp(baseResp.errCode);
            finish();
        }
    }

}
