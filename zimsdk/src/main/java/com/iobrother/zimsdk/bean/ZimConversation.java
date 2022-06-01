package com.iobrother.zimsdk.bean;

public class ZimConversation {
//    // 获取会话ID
//    public String getId() {
//        return "";
//    }
//
//    // 获取会话类型
//    public String getType() {
//        return "";
//    }
//
//    // 如果会话类型为单聊，返回的是对方用户ID，如果会话类型为群聊，返回的是群ID
//    public String getTarget() {
//        return "";
//    }
//
//    // 获取会话显示名称，其显示优先级如下：
//    // 群聊：群名称
//    // 单聊：对方好友备注->对方昵称->对方的用户ID
//    public String getDisplayName() {
//        return "";
//    }
//
//    // 获取会话展示头像
//    public String getAvatar() {
//        return "";
//    }
//
//    public int getUnreadCount() {
//        return 0;
//    }
//
//    public ZimMessage getLastMessage() {
//        return null;
//    }
//
//    // 获取未编辑完的草稿消息（只存本地，程序卸载重装后会丢失）
//    public String getDraft() {
//        return "";
//    }

    public String id;
    public int type;
    public long updatedAt;
    public String target;
    public long peerLastRead;
    public long peerLastRecv;
    public String isTop;
    public String lastMsg;
}
