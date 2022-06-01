package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.bean.ZimConversation;

import java.util.List;

public abstract class ZimConversationListener {

    // 新会话
    public void onNewConversation(List<ZimConversation> conversationList) {

    }

    // 某些会话的关键信息发生变化（未读计数发生变化、最后一条消息被更新等等），可以根据会话的 lastMessage -> timestamp 重新对会话列表做排序
    public void onConversationChanged(List<ZimConversation> conversationList) {

    }
}
