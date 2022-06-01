package com.iobrother.zimsdk.manager;

import android.util.Log;

import com.iobrother.zimsdk.ZimClient;
import com.iobrother.zimsdk.bean.ZimConversation;
import com.iobrother.zimsdk.bean.ZimMessage;
import com.iobrother.zimsdk.listener.ZimCallback;
import com.iobrother.zimsdk.listener.ZimCallbackProxy;
import com.iobrother.zimsdk.listener.ZimConversationListener;
import com.iobrother.zimsdk.listener.ZimResultCallback;
import com.iobrother.zimsdk.utils.JSONUtils;

import java.util.List;

import sdk.Sdk;
import sdktype.ConversationListener;

public class ZimConversationManager {
    private ZimClient client;
    public ZimConversationManager(ZimClient client) {
        this.client = client;
    }

    public void setConversationListener(ZimConversationListener listener) {
        sdk.Sdk.setConversationListener(new ConversationListener() {
            @Override
            public void onConversationChanged(String s) {
                List<ZimConversation> list = JSONUtils.toArray(s, ZimConversation.class);
                listener.onConversationChanged(list);
            }

            @Override
            public void onNewConversation(String s) {
                List<ZimConversation> list = JSONUtils.toArray(s, ZimConversation.class);
                listener.onNewConversation(list);

            }
        });
    }

    // 获取本地会话列表
    public void loadAllConversations(ZimResultCallback<List<ZimConversation>> callback) {
        Sdk.loadAllConversations(ZimCallbackProxy.listCallback(callback, ZimConversation.class));
    }

    public void getConversation(String id, ZimResultCallback<ZimConversation> callback) {

    }

    // 获取会话消息列表
    // direction 1： 上翻，拉取旧消息 2：下翻，拉取新消息
    public void loadConversationMessages(int count, ZimMessage startMsg, int direction, ZimResultCallback<List<ZimMessage>> callback) {
    }

    // 删除会话
    // 删除本地会话的同时，服务器也会同步删除
    // 会话内的消息在本地删除的同时，在服务器也会同步删除
    public void deleteConversation(String id) {

    }

    public void setConversationTop(String id, boolean isTop) {

    }

    public void setConversationDraft(String id, ZimCallback callback) {

    }

    public int getTotalUnreadMessageCount() {
        return 0;
    }
}
