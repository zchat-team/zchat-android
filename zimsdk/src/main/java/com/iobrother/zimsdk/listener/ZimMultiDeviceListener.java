package com.iobrother.zimsdk.listener;

import java.util.List;

public abstract class ZimMultiDeviceListener {
    public void onContactEvent(int event, String target, String extend) {

    }

    public void onGroupEvent(int event, String target, List<String> users) {

    }

    // 置顶 取消置顶 免打扰 取消免打扰 等
    public void onConversationEvent(int event, String id) {

    }
}
