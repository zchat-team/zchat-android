package com.iobrother.zchat;

import android.content.Context;

import com.iobrother.zchat.data.bean.user.UserInfo;
import com.iobrother.zchat.data.source.cache.AppCache;
import com.iobrother.zimsdk.ZimClient;
import com.iobrother.zimsdk.ZimConfig;
import com.iobrother.zimsdk.listener.ZimSDKListener;

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
                    super.onConnecting();
                }

                @Override
                public void onConnected() {
                    super.onConnected();
                }

                @Override
                public void onDisconnected(int code, String msg) {
                    super.onDisconnected(code, msg);
                }

                @Override
                public void onSync(int status) {
                    super.onSync(status);
                }
            });
        }
    }
}
