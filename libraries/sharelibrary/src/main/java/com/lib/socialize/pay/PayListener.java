package com.lib.socialize.pay;

/**
 * Created by asus on 2017/3/21.
 */

public interface PayListener {
    void onPaySuccess(PayManager.PayType type);
    void onPayFaild(int error_code);
    void onCancel();
}
