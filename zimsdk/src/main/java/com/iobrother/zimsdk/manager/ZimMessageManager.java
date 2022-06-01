package com.iobrother.zimsdk.manager;

import android.util.Log;

import com.iobrother.zimsdk.ZimClient;
import com.iobrother.zimsdk.bean.ZimMessage;
import com.iobrother.zimsdk.listener.ZimCallback;
import com.iobrother.zimsdk.listener.ZimMessageListener;
import com.iobrother.zimsdk.listener.ZimResultCallback;
import com.iobrother.zimsdk.listener.ZimSendCallback;
import com.iobrother.zimsdk.utils.JSONUtils;

import java.util.List;

import sdk.Sdk;
import sdktype.MessageListener;
import sdktype.SendCallback;

public class ZimMessageManager {
    private ZimClient client;
    public ZimMessageManager(ZimClient client) {
        this.client = client;
    }

    public void sendMessage(ZimMessage msg, ZimSendCallback callback) {
        sdktype.SendReq req = new sdktype.SendReq();
        req.setType(msg.getType().ordinal());
        req.setConvType(msg.getConvType().ordinal());
        req.setTarget(msg.getTarget());
        req.setContent(msg.getContent());
        Sdk.send(req, new SendCallback() {
            @Override
            public void onError(long l, String s) {
                callback.onError((int)l, s);
            }

            @Override
            public void onProgress(long l) {
                callback.onProgress((int)l);
            }

            @Override
            public void onSuccess(String s) {
                ZimMessage msg = JSONUtils.toObj(s, ZimMessage.class);
                callback.onSuccess(msg);
            }
        });
    }

    public void setMessageListener(ZimMessageListener listener) {
        sdk.Sdk.setMessageListener(new MessageListener() {
            @Override
            public void onMessageRecalled(long l) {
                listener.onMessageRecalled(l);
            }

            @Override
            public void onNewMessage(String s) {
//                listener.onNewMessage(s);
            }

            @Override
            public void onReadReceipt(String s) {
//                listener.onReadReceipt();
            }
        });
    }

    // 撤回消息
    public void recallMessage(ZimMessage msg, ZimCallback callback) {

    }

    // 转发消息
    // 未发送成功、引用消息不支持转发
    public void forwardMessage(ZimMessage msg, String target, ZimCallback callback) {

    }

    // 删除消息，删除本地消息，同时服务器上的消息也一起删除
    public void deleteMessage(ZimMessage msg, ZimCallback callback) {

    }

    // 删除多条消息，要删除的消息必须属于同一个会话，一次最多删除30条
    public void deleteMessages(List<ZimMessage> msgs, ZimCallback callback) {

    }

    // 向本地存储插入消息
    public String insertMessage(ZimMessage msg, ZimResultCallback<ZimMessage> callback) {
        return  "";
    }

    // 搜索消息
    public void searchMessages(String target, String keyword, long offset, int count, ZimResultCallback<List<ZimMessage>> callback) {
        // 得到会话对象，调用会话对象搜索消息
    }
}
