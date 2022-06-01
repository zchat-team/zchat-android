package com.iobrother.zimsdk.listener;

public interface ZimCallback<T> {
    void onSuccess(T t);
    void onError(int code, String message);
}
