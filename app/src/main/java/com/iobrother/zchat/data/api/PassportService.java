package com.iobrother.zchat.data.api;

import com.iobrother.zchat.data.bean.passport.SmsLoginReq;
import com.iobrother.zchat.data.bean.passport.SmsLoginRsp;
import com.iobrother.zchat.data.bean.passport.SmsReq;
import com.iobrother.zchat.data.bean.passport.SmsRsp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PassportService {

    @POST("passport/sms")
    Call<SmsRsp> sms(@Body SmsReq req);

    @POST("passport/smsLogin")
    Call<SmsLoginRsp> smsLogin(@Body SmsLoginReq req);
}
