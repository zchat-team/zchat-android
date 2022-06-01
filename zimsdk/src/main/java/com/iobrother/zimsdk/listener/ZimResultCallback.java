package com.iobrother.zimsdk.listener;

public interface ZimResultCallback<T> {
    void onSuccess(T t);
    void onError(int code, String message);
}
