package com.iobrother.zchat.data.source.cache;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.iobrother.zchat.data.bean.user.UserInfo;
import com.tencent.mmkv.MMKV;

public class AppCache {
    public static void init(Context context) {
        MMKV.initialize(context);
    }

    public static Boolean isLogin() {
        MMKV kv = MMKV.mmkvWithID("app");
        return kv.decodeBool("login", false);
    }

    public static void setLogin(Boolean isLogin) {
        MMKV kv = MMKV.mmkvWithID("app");
        kv.encode("login", isLogin);
    }

    public static UserInfo getUserInfo() {
        MMKV kv = MMKV.mmkvWithID("app");
        String user = kv.decodeString("user");
        if (TextUtils.isEmpty(user)) {
            return null;
        }
        return new Gson().fromJson(user, UserInfo.class);
    }

    public static void setUserInfo(UserInfo userInfo) {
        MMKV kv = MMKV.mmkvWithID("app");
        if (userInfo == null) {
            kv.encode("user", "");
            setLogin(false);
        } else {
            kv.encode("user", new Gson().toJson(userInfo));
            setLogin(true);
        }
    }
}
