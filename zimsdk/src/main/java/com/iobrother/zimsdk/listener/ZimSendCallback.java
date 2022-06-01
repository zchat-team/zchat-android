package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.bean.ZimMessage;

public interface ZimSendCallback extends ZimCallback<ZimMessage> {
    void onSuccess(ZimMessage msg);
    void onError(int code, String message);
    void onProgress(int progress);
}
