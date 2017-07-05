package com.lib.socialize.pay;

import android.content.Context;

import com.lib.socialize.pay.alipay.Alipay;
import com.lib.socialize.pay.weixin.WXPay;

/**
 * Created by asus on 2017/3/21.
 */

public  class PayManager {
    private static PayManager instance;
    private PayListener payListener;
    public static final int NO_OR_LOW_WX = 1;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 2;  //支付参数错误
    public static final int ERROR_PAY = 3;  //支付失败
    public static final int ERROR_RESULT = 4;   //支付结果解析错误
    public static final int ERROR_NETWORK = 5;  //网络连接错误
    public static final int ERROR_DEALING = 6;  //
    private PayManager (){}

    public static synchronized PayManager getInstance(){
        if(instance==null)
            instance = new PayManager();
        return instance;
    }

    public void doPay(Context context, String type,String payparams, PayListener payListener){
        this.payListener = payListener;
        switch (type){
            case "wechat":
                Pay wxPay = new WXPay(context);
                wxPay.doPay(payparams,payListener);
                break;
            case "alipay":
                Alipay alipay = new Alipay(context);
                alipay.doPay(payparams,payListener);
                break;
        }

    }


    public  String getWXAppid(){
        return "";
    }


    //微信支付回调响应
    public void onResp(int error_code) {
        if(payListener == null) {
            return;
        }
        if(error_code == 0) {   //成功
            payListener.onPaySuccess(PayType.WXPAY);
        } else if(error_code == -1) {   //错误
            payListener.onPayFaild(ERROR_PAY);
        } else if(error_code == -2) {   //取消
            payListener.onCancel();
        }
    }

    public void release(){
        payListener=null;
        instance = null;
    }

    public enum PayType{
        WXPAY,ALIPAY
    }
}
