package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.bean.ZimMessage;
import com.iobrother.zimsdk.bean.ZimMessageReceipt;

import java.util.List;

public abstract class ZimMessageListener {
    // 收到新消息
    public void onNewMessage(ZimMessage msg) {

    }

    // 收到透传消息
    public void onNewCmdMessage(ZimMessage msg) {

    }

    // 已读回执
    public void onReadReceipt(List<ZimMessageReceipt> receiptList) {

    }

    // 已送达回执
    public void onDeliveredReceipt(List<ZimMessageReceipt> receiptList) {

    }

    // 撤回消息
    public void onMessageRecalled(long id) {

    }
}
