package com.iobrother.zchat;

import android.content.Context;
import android.util.Log;

import com.iobrother.zchat.data.bean.user.UserInfo;
import com.iobrother.zchat.data.source.cache.AppCache;
import com.iobrother.zimsdk.ZimClient;
import com.iobrother.zimsdk.ZimConfig;
import com.iobrother.zimsdk.bean.ZimConversation;
import com.iobrother.zimsdk.bean.ZimMessage;
import com.iobrother.zimsdk.bean.ZimMessageReceipt;
import com.iobrother.zimsdk.listener.ZimConversationListener;
import com.iobrother.zimsdk.listener.ZimErrorListener;
import com.iobrother.zimsdk.listener.ZimMessageListener;
import com.iobrother.zimsdk.listener.ZimResultCallback;
import com.iobrother.zimsdk.listener.ZimSDKListener;
import com.iobrother.zimsdk.listener.ZimSendCallback;

import java.util.List;

public class ZimHelper {
    private static ZimHelper instance = new ZimHelper();

    private ZimHelper() {}
    public static ZimHelper getInstance() {
        return instance;
    }

    public void init(Context context) {
        UserInfo user = AppCache.getUserInfo();
        if (user != null) {
            ZimConfig conf = new ZimConfig();
            conf.serverAddr = "172.16.5.188:2001";
            conf.dataDir = context.getExternalCacheDir().getAbsolutePath();
            conf.user = String.valueOf(user.uid);
            conf.deviceId = "A1001";
            conf.deviceName = "安卓设备";
            conf.token = "dummy token";
            conf.platform = "android";
            ZimClient.get().init(conf, new ZimSDKListener() {
                @Override
                public void onConnecting() {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onConnected() {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onDisconnected(int code, String msg) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onSync(int status) {
                    // TODO: 发送共享事件通知页面刷新
                }
            });

            ZimClient.get().conversationManager().setConversationListener(new ZimConversationListener() {
                @Override
                public void onNewConversation(List<ZimConversation> conversationList) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onConversationChanged(List<ZimConversation> conversationList) {
                    // TODO: 发送共享事件通知页面刷新
                }
            });

            ZimClient.get().messageManager().setMessageListener(new ZimMessageListener() {
                @Override
                public void onNewMessage(ZimMessage msg) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onNewCmdMessage(ZimMessage msg) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onReadReceipt(List<ZimMessageReceipt> receiptList) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onDeliveredReceipt(List<ZimMessageReceipt> receiptList) {
                    // TODO: 发送共享事件通知页面刷新
                }

                @Override
                public void onMessageRecalled(long id) {
                    // TODO: 发送共享事件通知页面刷新
                }
            });

            ZimClient.get().setErrorListener(new ZimErrorListener() {
                @Override
                public void onError(int code, String message) {
                    // TODO: 发送共享事件通知页面刷新
                }
            });

        }
    }

    public void send(ZimMessage msg, ZimSendCallback callback) {
        ZimClient.get().send(msg, callback);
    }

    public void loadAllConversations() {
        ZimClient.get().conversationManager().loadAllConversations(new ZimResultCallback<List<ZimConversation>>() {
            @Override
            public void onSuccess(List<ZimConversation> zimConversations) {
                // TODO: 发送共享事件通知页面刷新
            }

            @Override
            public void onError(int code, String message) {
                // TODO: 发送共享事件通知页面刷新
            }
        });
    }
}
