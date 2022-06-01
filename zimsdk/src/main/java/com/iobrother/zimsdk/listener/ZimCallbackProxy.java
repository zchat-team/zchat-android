package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.utils.JSONUtils;

import java.util.List;

public class ZimCallbackProxy {
    public sdktype.Callback stringCallback(ZimResultCallback<String> callback) {
        return new StringCallback(callback);
    }

    public static <T> sdktype.Callback listCallback(ZimResultCallback<List<T>> callback, Class<T> clazz) {
        return new ListCallback<>(callback, clazz);
    }

    public static <T> sdktype.Callback objectCallback(ZimResultCallback<T> callback, Class<T> clazz) {
        return new ObjectCallback<>(callback, clazz);
    }


    private static class ObjectCallback<T> implements sdktype.Callback {
        ZimResultCallback<T> callback;
        Class<T> clazz;

        public ObjectCallback(ZimResultCallback<T> callback, Class<T> clazz) {
            this.callback = callback;
            this.clazz = clazz;
        }

        @Override
        public void onError(long l, String s) {
            callback.onError((int)l, s);
        }

        @Override
        public void onSuccess(String s) {
            callback.onSuccess(JSONUtils.toObj(s, clazz));
        }
    }

    private static class ListCallback<T> implements sdktype.Callback {
        ZimResultCallback<List<T>> callback;
        Class<T> clazz;

        public ListCallback(ZimResultCallback<List<T>> callback, Class<T> clazz) {
            this.callback = callback;
            this.clazz = clazz;
        }

        @Override
        public void onError(long l, String s) {
            callback.onError((int)l, s);
        }

        @Override
        public void onSuccess(String s) {
            callback.onSuccess(JSONUtils.toArray(s, clazz));
        }
    }

    private static class StringCallback implements sdktype.Callback {
        ZimResultCallback<String> callback;
        public StringCallback(ZimResultCallback<String> callback) {
            this.callback = callback;
        }

        @Override
        public void onError(long l, String s) {
            callback.onError((int)l, s);
        }

        @Override
        public void onSuccess(String s) {
            callback.onSuccess(s);
        }
    }
}
