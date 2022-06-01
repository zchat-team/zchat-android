package com.iobrother.zimsdk.bean;

public class ZimMessageReceipt {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private String userId;
    private long timestamp = 0;

    public ZimMessageReceipt(String userId, long timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }
}
