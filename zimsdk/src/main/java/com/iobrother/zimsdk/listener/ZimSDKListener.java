package com.iobrother.zimsdk.listener;

public abstract class ZimSDKListener {
    public void onConnecting() {}
    public void onConnected() {}
    public void onDisconnected(int code, String msg) {} // TODO: 使用枚举 1：无效的令牌或者令牌过期 2：帐号冲突（已被其他端抢登）3：帐号被禁 4：被其他设备踢下线
    public void onSync(int status) {} // TODO: 使用枚举 1: 同步中 2：同步完成
}
