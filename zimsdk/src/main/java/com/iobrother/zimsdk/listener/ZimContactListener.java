package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.bean.ZimContactInfo;
import com.iobrother.zimsdk.bean.ZimFriendApplication;

import java.util.List;

public abstract class ZimContactListener {
    // 好友申请新增通知，两种情况会收到这个回调：
    // 自己申请加别人好友
    // 别人申请加自己好友
    public void onFriendAdded(List<ZimContactInfo> users) {

    }

    // 好友申请删除通知，四种情况会收到这个回调
    // 主动删除好友申请
    // 拒绝好友申请
    // 同意好友申请
    // 申请加别人好友被拒绝
    public void onFriendDeleted(List<String> users) {

    }

    public void onFriendApplicationListAdded(List<ZimFriendApplication> applicationList) {

    }

    public void onFriendApplicationListDeleted(List<String> users) {

    }

}
