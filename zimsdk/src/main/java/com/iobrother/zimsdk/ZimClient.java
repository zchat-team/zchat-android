package com.iobrother.zimsdk;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.iobrother.zimsdk.bean.ZimConversation;
import com.iobrother.zimsdk.bean.ZimMessage;
import com.iobrother.zimsdk.listener.ZimErrorListener;
import com.iobrother.zimsdk.listener.ZimResultCallback;
import com.iobrother.zimsdk.listener.ZimSDKListener;
import com.iobrother.zimsdk.listener.ZimSendCallback;
import com.iobrother.zimsdk.manager.ZimContactManager;
import com.iobrother.zimsdk.manager.ZimConversationManager;
import com.iobrother.zimsdk.manager.ZimGroupManager;
import com.iobrother.zimsdk.manager.ZimMessageManager;

import java.util.List;

import sdk.Sdk;
import sdktype.ErrorListener;
import sdktype.SDKListener;

public class ZimClient {
    private static final String TAG = "ZimClient";

    private static ZimClient sInstance = new ZimClient();
    private Context context;

    private ZimGroupManager groupManager;
    private ZimContactManager contactManager;
    private ZimConversationManager conversationManager;
    private ZimMessageManager messageManager;

    public ZimGroupManager groupManager() {
        if (groupManager == null) {
            synchronized (ZimGroupManager.class) {
                if (groupManager == null) {
                    groupManager = new ZimGroupManager(this);
                }
            }
        }

        return groupManager;
    }

    public ZimContactManager contactManager() {
        if (contactManager == null) {
            synchronized (ZimContactManager.class) {
                if (contactManager == null) {
                    contactManager = new ZimContactManager(this);
                }
            }
        }

        return contactManager;
    }

    public ZimConversationManager conversationManager() {
        if (conversationManager == null) {
            synchronized (ZimConversationManager.class) {
                if (conversationManager == null) {
                    conversationManager = new ZimConversationManager(this);
                }
            }
        }

        return conversationManager;
    }

    public ZimMessageManager messageManager() {
        if (messageManager == null) {
            synchronized (ZimMessageManager.class) {
                if (messageManager == null) {
                    messageManager = new ZimMessageManager(this);
                }
            }
        }

        return messageManager;
    }

    private ZimClient() {}
    public static ZimClient get() {
        return sInstance;
    }

    // TODO: 传递配置参数
    public void init(ZimConfig config, ZimSDKListener listener) {
        sdktype.Config conf = new sdktype.Config();
        conf.setServerAddr(config.serverAddr);
        conf.setDataDir(config.dataDir);
        conf.setUser(config.user);
        conf.setDeviceId(config.deviceId);
        conf.setDeviceName(config.deviceName);
        conf.setToken(config.token);
        conf.setPlatform(config.platform);

        sdk.Sdk.init(conf);
        sdk.Sdk.setSDKListener(new SDKListener() {
            @Override
            public void onConnected() {
                listener.onConnected();
            }

            @Override
            public void onConnecting() {
                listener.onConnecting();
            }

            @Override
            public void onDisconnected(long l, String s) {
                listener.onDisconnected((int)l, s);
            }

            @Override
            public void onSync(long l) {
                listener.onSync((int)l);
            }
        });
    }


    public void setErrorListener(ZimErrorListener listener) {
        sdk.Sdk.setErrorListener(new ErrorListener() {
            @Override
            public void onError(long l, String s) {
                listener.onError((int)l, s);
            }
        });
    }

    public void unInit() {
    }

    public void send(ZimMessage msg, ZimSendCallback callback) {
        messageManager().sendMessage(msg, callback);
    }

    public void loadAllConversations(ZimResultCallback<List<ZimConversation>> callback) {
        conversationManager().loadAllConversations(callback);
    }

    public void loadConversationMessages(int count, ZimMessage startMsg, int direction, ZimResultCallback<List<ZimMessage>> callback) {
        conversationManager().loadConversationMessages(count, startMsg, direction, callback);
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
