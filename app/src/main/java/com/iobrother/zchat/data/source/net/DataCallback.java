package com.iobrother.zchat.data.source.net;

import com.iobrother.zchat.data.source.cache.AppCache;
import com.kunminx.architecture.data.response.DataResult;
import com.kunminx.architecture.data.response.ResponseStatus;
import com.kunminx.architecture.data.response.ResultSource;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

//回调统一拦截处理
public abstract class DataCallback<T> implements Callback<T> {

    protected abstract void callback(DataResult dataResult);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        Boolean isSuccess = response.isSuccessful();
        if (!isSuccess) {
            //解析异常json
            ServerError errorRsp = parseError(response);

            //todo 做特殊应答码处理，如异地登录、token失效（过期或者被吊销）等，后期处理
            if (errorRsp.code == 401 || errorRsp.code == 1004) {
                //无效令牌
//                LogUtil.d("无效令牌"+errorRsp.code);
//                AppCache.setPhone("");
                AppCache.setLogin(false);
                AppCache.setUserInfo(null);
//                RxBus.sendMsg(RxMsgCode.token_invalid, null);
            }

//            ResponseStatus errorStatus = new ResponseStatus(
//                    String.valueOf(errorRsp.code), isSuccess, ResultSource.NETWORK, errorRsp.message);
//            callback(new DataResult<>(response.body(), errorStatus));
            return;
        }

        ResponseStatus responseStatus = new ResponseStatus(
                String.valueOf(response.code()), response.isSuccessful(), ResultSource.NETWORK);
        callback(new DataResult<>(response.body(), responseStatus));
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
//        ResponseStatus failStatus = new ResponseStatus(
//                "-1", false, ResultSource.NETWORK, t.getMessage());
//        callback(new DataResult<>(null, failStatus));
    }

    public static class ServerError {
        public int code;
        public String message;
        public String detail;
    }

    private ServerError parseError(Response<?> response) {
        Converter<ResponseBody, ServerError> converter = RetrofitHelper.getInstance().getRetrofit()
                .responseBodyConverter(ServerError.class, new Annotation[0]);
        ServerError error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ServerError();
        }
        return error;
    }
}
