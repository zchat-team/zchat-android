package com.iobrother.zimsdk.bean;

public class ZimMessage {
    public enum Type {
        UNKNOWN, TXT, IMAGE, VIDEO, LOCATION, VOICE, FILE, CMD, CUSTOM
    }

    public enum Direct {
        SEND, RECEIVE
    }

    public enum Status {
        SUCCESS, FAIL, INPROGRESS, CREATE
    }

    public enum ConvType {
        Unknown,
        Single,
        Group,
        Room
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ConvType getConvType() {
        return convType;
    }

    public void setConvType(ConvType convType) {
        this.convType = convType;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public static ZimMessage createTextMessage(ConvType convType, String target, String content) {
        ZimMessage msg = new ZimMessage();
        msg.type = Type.TXT;
        msg.convType = convType;
        msg.content = content;
        msg.sender = "";
        msg.target = target;
        msg.id = "";
        msg.key = "";
        msg.sendTime = 0;

        return msg;
    }

    // TODO: createImageMessage、createVoiceMessage、createVideoMessage etc.

    public String id;   // 本地消息ID
    public String key; // 远程ID
    public String sender;
    public String target;
    public String content;
    public Type type;
    public ConvType convType;
    public long sendTime;
}
