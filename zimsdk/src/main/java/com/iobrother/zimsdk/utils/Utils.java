package com.iobrother.zimsdk.utils;

import android.os.Handler;
import android.os.Looper;

import com.iobrother.zimsdk.listener.ZimResultCallback;

import java.util.List;

public class Utils {
    private final static Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static void runMainThread(Runnable runnable) {
        MAIN_HANDLER.post(runnable);
    }

    public static <T> void returnError(ZimResultCallback<T> callback, int code, String message) {
        if (callback != null) {
            Utils.runMainThread(() -> {
                callback.onError(code, message);
            });
        }
    }

    public static <T> void returnObject(ZimResultCallback<T> callback, Class<T> clazz, String result) {
        if (callback != null) {
            Utils.runMainThread(() -> {
                callback.onSuccess(JSONUtils.toObj(result, clazz));
            });
        }
    }

    public static <T> void returnString(ZimResultCallback<String> callback, String result) {
        if (callback != null) {
            Utils.runMainThread(() -> {
                callback.onSuccess(JSONUtils.toString(result));
            });
        }
    }

    public static <T> void returnList(ZimResultCallback<List<T>> callback, Class<T> clazz, String result) {
        if (callback != null) {
            Utils.runMainThread(() -> {
                callback.onSuccess(JSONUtils.toArray(result, clazz));
            });
        }
    }

}
