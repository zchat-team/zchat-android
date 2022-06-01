package com.iobrother.zimsdk.listener;

import com.iobrother.zimsdk.bean.ZimGroupChangeInfo;
import com.iobrother.zimsdk.bean.ZimGroupMemberInfo;

import java.util.List;

public abstract class ZimGroupListener {
    public void onMemberJoin(String groupId, ZimGroupMemberInfo member) {

    }

    public void onMemberQuit(String groupId, ZimGroupMemberInfo member) {

    }

    public void onMemberInvited(String groupId, ZimGroupMemberInfo operator, List<ZimGroupMemberInfo> memberList) {

    }

    public void onMemberKicked(String groupId, ZimGroupMemberInfo operator, List<ZimGroupMemberInfo> memberList) {

    }

    // 用于多端同步
    public void onGroupCreated(String groupId) {

    }

    // 群解散
    public void onGroupDestroyed() {

    }

    public void onGroupInfoUpdated(String groupId, List<ZimGroupChangeInfo> infoList) {

    }


    public void onRecvJoinApplication(String groupId, ZimGroupMemberInfo member, String reason) {

    }

    public void onApplicatoinProcessed(String groupId, ZimGroupMemberInfo operator, boolean isAgree, String reason) {

    }

    public void onGrantAdmin(String groupId, ZimGroupMemberInfo operator, List<ZimGroupMemberInfo> memberList) {

    }

    public void onRevokeAdmin(String groupId, ZimGroupMemberInfo operator, List<ZimGroupMemberInfo> memberList) {

    }

    // 主要用于多端同步
    public void onQuitGroup(String groupId) {

    }
}
