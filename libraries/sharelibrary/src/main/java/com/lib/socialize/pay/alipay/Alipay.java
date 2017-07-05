package com.lib.socialize.pay.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.lib.socialize.pay.Pay;
import com.lib.socialize.pay.PayListener;
import com.lib.socialize.pay.PayManager;

import java.util.Map;

public class Alipay  implements Pay{
    private String mParams;
    private PayTask mPayTask;
    private PayListener mCallback;
    final Handler handler = new Handler();

    public Alipay(Context context) {
        mPayTask = new PayTask((Activity) context);
    }
    //支付
    public void doPay(String params, PayListener callback) {
        mParams = params;
        mCallback=null;
        mCallback = callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Map<String, String> pay_result = mPayTask.payV2(mParams, true);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCallback == null) {
                            return;
                        }

                        if (pay_result == null) {
                            mCallback.onPayFaild(PayManager.ERROR_RESULT);
                            return;
                        }

                        String resultStatus = pay_result.get("resultStatus");
                        if (TextUtils.equals(resultStatus, "9000")) {    //支付成功
                            mCallback.onPaySuccess(PayManager.PayType.ALIPAY);
                        } else if (TextUtils.equals(resultStatus, "8000")) { //支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            mCallback.onPayFaild(PayManager.ERROR_DEALING);
                        } else if (TextUtils.equals(resultStatus, "6001")) {        //支付取消
                            mCallback.onCancel();
                        } else if (TextUtils.equals(resultStatus, "6002")) {     //网络连接出错
                            mCallback.onPayFaild(PayManager.ERROR_NETWORK);
                        } else if (TextUtils.equals(resultStatus, "4000")) {        //支付错误
                            mCallback.onPayFaild(PayManager.ERROR_PAY);
                        }
                    }
                });
            }
        }).start();
    }
}
