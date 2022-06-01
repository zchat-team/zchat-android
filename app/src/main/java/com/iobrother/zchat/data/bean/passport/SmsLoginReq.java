package com.iobrother.zchat.data.bean.passport;

public class SmsLoginReq {
    private String mobile;
    private String code;

    public SmsLoginReq() {
    }

    public SmsLoginReq(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
