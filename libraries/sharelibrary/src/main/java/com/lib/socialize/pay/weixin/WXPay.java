package com.lib.socialize.pay.weixin;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lib.socialize.pay.Pay;
import com.lib.socialize.pay.PayListener;
import com.lib.socialize.pay.PayManager;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class WXPay implements Pay {

    private static WXPay mWXPay;
    private IWXAPI mWXApi;
    private String mPayParam;
    private PayListener mCallback;


    public WXPay(Context context) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
    }

    /**
     * 发起微信支付
     */
    public void doPay(String pay_param, PayListener callback) {
        mPayParam = pay_param;
        mCallback=null;
        mCallback = callback;
        if(!check()) {
            if(mCallback != null) {
                mCallback.onPayFaild(PayManager.NO_OR_LOW_WX);
            }
            return;
        }

        JSONObject param = null;
        try {
            param = new JSONObject(mPayParam);
        } catch (JSONException e) {
            e.printStackTrace();
            if(mCallback != null) {
                mCallback.onPayFaild(PayManager.ERROR_PAY_PARAM);
            }
            return;
        }
        if(TextUtils.isEmpty(param.optString("appid")) || TextUtils.isEmpty(param.optString("partnerid"))
                || TextUtils.isEmpty(param.optString("prepayid")) || TextUtils.isEmpty(param.optString("packageName")) ||
                TextUtils.isEmpty(param.optString("noncestr")) || TextUtils.isEmpty(param.optString("timestamp")) ||
                TextUtils.isEmpty(param.optString("sign"))) {
            if(mCallback != null) {
                mCallback.onPayFaild(PayManager.ERROR_PAY_PARAM);
            }
            return;
        }
        mWXApi.registerApp(param.optString("appid"));
        PayReq req = new PayReq();
        req.appId = param.optString("appid");
        req.partnerId = param.optString("partnerid");
        req.prepayId = param.optString("prepayid");
        req.packageValue = param.optString("packageName");
        req.nonceStr = param.optString("noncestr");
        req.timeStamp = param.optString("timestamp");
        req.sign = param.optString("sign");
        boolean result = req.checkArgs();
        if(result){
            mWXApi.sendReq(req);
        }
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
