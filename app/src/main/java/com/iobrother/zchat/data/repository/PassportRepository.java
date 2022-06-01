package com.iobrother.zchat.data.repository;

import android.util.Log;

import com.iobrother.zchat.data.api.PassportService;
import com.iobrother.zchat.data.bean.passport.SmsLoginReq;
import com.iobrother.zchat.data.bean.passport.SmsLoginRsp;
import com.iobrother.zchat.data.source.net.RetrofitHelper;
import com.kunminx.architecture.data.response.DataResult;
import com.kunminx.architecture.data.response.ResponseStatus;
import com.kunminx.architecture.data.response.ResultSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PassportRepository {
    private static final PassportRepository S_REQUEST_MANAGER = new PassportRepository();

    private PassportRepository() {
    }

    public static PassportRepository getInstance() {
        return S_REQUEST_MANAGER;
    }

    private Call<SmsLoginRsp> smsLoginRspCall;

    public void smsLogin(SmsLoginReq req, DataResult.Result<SmsLoginRsp> result) {
        Log.d("TEST", "smsLogin: ");
        Retrofit retrofit = RetrofitHelper.getInstance().getRetrofit();
        smsLoginRspCall = retrofit.create(PassportService.class).smsLogin(req);
        smsLoginRspCall.enqueue(new Callback<SmsLoginRsp>() {
            @Override
            public void onResponse(Call<SmsLoginRsp> call, Response<SmsLoginRsp> response) {
                Log.d("TEST", "succ: ");
                ResponseStatus responseStatus = new ResponseStatus(
                        String.valueOf(response.code()), response.isSuccessful(), ResultSource.NETWORK);
                result.onResult(new DataResult<>(response.body(), responseStatus));
                smsLoginRspCall = null;
            }

            @Override
            public void onFailure(Call<SmsLoginRsp> call, Throwable t) {
                Log.d("TEST", "onFailure: " + t.getMessage());
                result.onResult(new DataResult<>(null,
                        new ResponseStatus(t.getMessage(), false, ResultSource.NETWORK)));
                smsLoginRspCall = null;
            }
        });
    }

    public void cancelSmsLogin() {
        if (smsLoginRspCall != null && !smsLoginRspCall.isCanceled()) {
            smsLoginRspCall.cancel();
            smsLoginRspCall = null;
        }
    }
}
